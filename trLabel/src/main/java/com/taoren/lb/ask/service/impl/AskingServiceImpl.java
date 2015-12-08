package com.taoren.lb.ask.service.impl;

import com.taoren.common.util.TaorenUtils;
import com.taoren.lb.ask.dao.AskingDao;
import com.taoren.lb.ask.dao.AskingMediaDao;
import com.taoren.lb.label.dao.LabelDao;
import com.taoren.lb.label.dao.LabelMediaDao;
import com.taoren.lb.label.dao.LabelZanDao;
import com.taoren.model.ask.Asking;
import com.taoren.model.ask.AskingMedia;
import com.taoren.model.lb.Label;
import com.taoren.model.lb.LabelMedia;
import com.taoren.model.lb.LabelZan;
import com.taoren.service.ask.AskingRemoteService;
import com.taoren.service.ask.model.AskingListRespDto;
import com.taoren.service.ask.model.AskingMediaListRespDto;
import com.taoren.service.ask.model.AskingRespDto;
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
public class AskingServiceImpl implements AskingRemoteService {

    Logger logger = LoggerFactory.getLogger(AskingServiceImpl.class);

    @Autowired
    private AskingDao askingDao;

    @Autowired
    private AskingMediaDao askingMediaDao;

    public AskingRespDto addAsking(Asking asking) {
        AskingRespDto respDto = new AskingRespDto();
        asking.setReplyCount(0);

        askingDao.insertAsking(asking);

        List<AskingMedia> mediaList = asking.getMediaList();

        if(mediaList != null && mediaList.size()>0){
            for(AskingMedia am: mediaList){

                am.setStatus(1);
                am.setAskingId(asking.getId());
                am.setMediaType(1);

                am.setId(Long.parseLong(am.getUrl().substring(am.getUrl().lastIndexOf("/") + 1, am.getUrl().length())));

            }

            askingMediaDao.insertAskingMediaList(mediaList);
        }



        respDto.setResultCode(1);
        respDto.setAskingId(asking.getId());
        respDto.setTime(new Date());
        respDto.setMsg("Add success");

        return respDto;
    }

    public String addAsking4Json(String askingJson) {
        return TaorenUtils.o2j(addAsking(TaorenUtils.j2o(askingJson, Asking.class)));
    }

    public AskingRespDto delAsking(Asking asking) {
        AskingRespDto respDto = new AskingRespDto();


        if(askingDao.deleteAsking(asking) > 0){
            respDto.setResultCode(1);
            respDto.setTime(new Date());
            respDto.setMsg("删除成功");

        }else{
            respDto.setResultCode(2);
            respDto.setMsg("删除失败");
        }

        return respDto;
    }

    public String delAsking4Json(String askingJson) {
        return TaorenUtils.o2j(delAsking(TaorenUtils.j2o(askingJson, Asking.class)));
    }

    public AskingListRespDto getAsking(Asking asking) {
        AskingListRespDto respDto = new AskingListRespDto();

        List<Asking> askingList = null;
        //不允许全查
        if(asking.getId() != null || asking.getUid() != null){
            askingList = askingDao.selectAsking(asking);

            AskingMedia am = new AskingMedia();
            for(Asking a: askingList){
                am.setAskingId(a.getId());
                a.setMediaList(askingMediaDao.selectAskingMedia(am));
            }
        }

        respDto.setResultCode(1);
        respDto.setList(askingList);

        return respDto;
    }



    public String getAsking4Json(String askingJson) {
        return TaorenUtils.o2j(getAsking(TaorenUtils.j2o(askingJson, Asking.class)));
    }

    public AskingListRespDto askingList(Map map) {
        AskingListRespDto respDto = new AskingListRespDto();
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


            respDto.setTotalRow(askingDao.askingCount(map));
            respDto.setList(askingDao.askingList(map));
            respDto.setResultCode(1);
        }catch (Exception e){
            e.printStackTrace();
            respDto.setResultCode(2);
        }

        return respDto;
    }

    public String askingList4Json(String paramJson) {
        return TaorenUtils.o2j(askingList(TaorenUtils.j2o(paramJson, HashMap.class)));
    }

    public AskingMediaListRespDto getAskingMedia(long askingId) {
        AskingMediaListRespDto respDto = new AskingMediaListRespDto();

        AskingMedia am = new AskingMedia();
        am.setAskingId(askingId);
        respDto.setList(askingMediaDao.selectAskingMedia(am));
        respDto.setResultCode(1);

        return respDto;
    }

    public String getAskingMedia4Json(long askingId) {
        return TaorenUtils.o2j(getAskingMedia(askingId));
    }
}
