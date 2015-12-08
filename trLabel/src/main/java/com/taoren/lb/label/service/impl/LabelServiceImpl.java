package com.taoren.lb.label.service.impl;

import com.taoren.common.util.TaorenUtils;
import com.taoren.lb.label.dao.LabelDao;
import com.taoren.lb.label.dao.LabelMediaDao;
import com.taoren.lb.label.dao.UserDao;
import com.taoren.lb.label.dao.LabelZanDao;
import com.taoren.model.lb.Label;
import com.taoren.model.lb.LabelMedia;
import com.taoren.model.lb.LabelZan;
import com.taoren.service.lb.LabelRemoteService;
import com.taoren.service.lb.model.LabelListRespDto;
import com.taoren.service.lb.model.LabelRespDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created by Administrator on 2015/5/25.
 */
@Service
public class LabelServiceImpl implements LabelRemoteService {

    Logger logger = LoggerFactory.getLogger(LabelServiceImpl.class);

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private LabelMediaDao labelMediaDao;

    @Autowired
    private LabelZanDao zanDao;

    /**
     * 增加
     * @param label
     * @return
     */
    public LabelRespDto addLabel(Label label) {
        LabelRespDto respDto = new LabelRespDto();
        label.setStatus(1);
        label.setZan(0);//默认为0赞
//        if(label.getLabelDetail() != null && label.getLabelDetail().length() > 280)

        labelDao.insertLabel(label);

        List<LabelMedia> mediaList = label.getMediaList();

        if(mediaList != null && mediaList.size()>0){
//            LabelMedia lm = null;

//            for(int i=0; i<mediaList.size(); i++){
//              lm = mediaList.get(i);
            for(LabelMedia lm: mediaList){

                lm.setStatus(1);
                lm.setLabelId(label.getId());
                lm.setMediaType(1);

                lm.setId(Long.parseLong(lm.getUrl().substring(lm.getUrl().lastIndexOf("/") + 1, lm.getUrl().length())));

            }

            labelMediaDao.insertLabelMediaList(mediaList);
        }



        respDto.setResultCode(1);
        respDto.setLabelId(label.getId());
        respDto.setTime(new Date());
        respDto.setMsg("添加成功");

        return respDto;
    }

    /**
     * 增加
     * @param labelJson
     * @return
     */
    public String addLabel4Json(String labelJson) {
        return TaorenUtils.o2j(addLabel(TaorenUtils.j2o(labelJson, Label.class)));
    }


    /**
     * 修改
     * @param label
     * @return
     */
    public LabelRespDto editLabel(Label label) {
        LabelRespDto respDto = new LabelRespDto();

        label.setZan(null);
        label.setStatus(null);

        labelDao.updateLabel(label);

        List<LabelMedia> mediaList = label.getMediaList();

        if(mediaList != null && mediaList.size() > 0){
//            LabelMedia lm = null;
            List<LabelMedia> deleteList = new ArrayList<LabelMedia>();
            List<LabelMedia> addList = new ArrayList<LabelMedia>();


//            for(int i=0; i<mediaList.size(); i++){
//                lm = mediaList.get(i);
            for(LabelMedia lm: mediaList){

                lm.setLabelId(label.getId());

                if("delete".equals(lm.getUrl())){
                    lm.setStatus(4);
                    lm.setMsg(lm.getUrl());
                    lm.setUrl(null);
                    deleteList.add(lm);
                }else{
                    lm.setStatus(1);
                    lm.setMediaType(1);
                    lm.setId(Long.parseLong(lm.getUrl().substring(lm.getUrl().lastIndexOf("/") + 1, lm.getUrl().length())));
                    addList.add(lm);
                }
            }

            if(addList.size() > 0 ){
                labelMediaDao.insertLabelMediaList(addList);
            }

            if(deleteList.size() > 0){
                labelMediaDao.updateLabelMediaList(deleteList);
            }

        }


        respDto.setResultCode(1);
        respDto.setTime(new Date());
        respDto.setMsg("修改成功");

        return respDto;
    }

    /**
     * 修改
     * @param labelJson
     * @return
     */
    public String editLabel4Json(String labelJson) {
        return TaorenUtils.o2j(editLabel(TaorenUtils.j2o(labelJson, Label.class)));
    }


    /**
     * 删除
     * @param label
     * @return
     */
    public LabelRespDto delLabel(Label label) {

        LabelRespDto respDto = new LabelRespDto();

        label.setStatus(4);//4失效

        if(labelDao.updateLabel(label) > 0){
            respDto.setResultCode(1);
            respDto.setTime(new Date());
            respDto.setMsg("删除成功");

        }else{
            respDto.setResultCode(2);
            respDto.setMsg("删除失败");
        }

        return respDto;
    }

    /**
     * 删除
     * @param labelJson
     * @return
     */
    public String delLabel4Json(String labelJson) {
        return TaorenUtils.o2j(delLabel(TaorenUtils.j2o(labelJson, Label.class)));
    }

    /**
     * 获取
     * @param label
     * @return
     */
    public LabelListRespDto getLabel(Label label) {

        LabelListRespDto respDto = new LabelListRespDto();

        if(label.getId() == null){
            label.setStatus(1);//只有根据uid来查时才需要status
        }else{
            label.setUid(null);
        }


        List<Label> labelList = null;
        //不允许全查
        if(label.getId() != null || label.getUid() != null){
            labelList = labelDao.selectLabel(label);

            LabelMedia lm = new LabelMedia();
            for(Label l: labelList){
                lm.setLabelId(l.getId());
                lm.setStatus(1);
                l.setMediaList(labelMediaDao.selectLabelMediaList(lm));

                l.setZanUserList(zanDao.zanUserList(l.getId()));
            }
        }

        respDto.setResultCode(1);
        respDto.setList(labelList);

        return respDto;
    }

    /**
     * 获取
     * @param labelJson
     * @return
     */
    public String getLabel4Json(String labelJson) {
        return TaorenUtils.o2j(getLabel(TaorenUtils.j2o(labelJson, Label.class)));
    }

    public int zanLabel(LabelZan zan) {
        if(zan.getAction() != null && zan.getAction() != 0){
            int ret = 0;
            if(zan.getAction() > 0){
                zan.setAction(1);
                ret =zanDao.insertZan(zan);
            }
            if(zan.getAction() < 0){
                zan.setAction(-1);
                ret = zanDao.deleteZan(zan);
            }

            return ret;
//            if(ret == 1){
//                return labelDao.updateLabelZan(zan);
//            }else{
//                return 0;
//            }
        }
        return 0;//不用更改
    }


    public int zanLabel4Json(String zanJson) {
        return zanLabel(TaorenUtils.j2o(zanJson, LabelZan.class));//不用更改
    }


    public LabelListRespDto labelList(Map map) {
        LabelListRespDto respDto = new LabelListRespDto();
        try{
            if(map.get("page_size") == null){
                map.put("page_size", 10);
            }
            if(map.get("curr_page") == null){
                map.put("curr_page", 1);
            }
            int curr_page  = (Integer)map.get("curr_page");
            int page_size = (Integer)map.get("page_size");
            map.put("start_row", (curr_page - 1) * page_size);

            if(map.get("status") == null){
                map.put("status", 1);
            }

            respDto.setTotalRow(labelDao.labelCount(map));
            respDto.setList(labelDao.labelList(map));
            respDto.setResultCode(1);
        }catch (Exception e){
            e.printStackTrace();
            respDto.setResultCode(2);
        }

        return respDto;
    }

    public String labelList4Json(String paramJson) {
        return TaorenUtils.o2j(labelList(TaorenUtils.j2o(paramJson, HashMap.class)));
    }

}
