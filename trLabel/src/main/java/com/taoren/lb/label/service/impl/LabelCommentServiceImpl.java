package com.taoren.lb.label.service.impl;

import com.taoren.common.util.TaorenUtils;
import com.taoren.lb.label.dao.LabelCommentDao;
import com.taoren.lb.label.dao.LabelDao;
import com.taoren.lb.label.dao.LabelMediaDao;
import com.taoren.lb.label.dao.LabelZanDao;
import com.taoren.model.lb.LabelComment;
import com.taoren.service.lb.LabelCommentRemoteService;
import com.taoren.service.lb.model.LabelCommentListRespDto;
import com.taoren.service.lb.model.LabelCommentRespDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created by Administrator on 2015/5/25.
 */
@Service
public class LabelCommentServiceImpl implements LabelCommentRemoteService {

    Logger logger = LoggerFactory.getLogger(LabelCommentServiceImpl.class);

    @Autowired
    private LabelCommentDao labelCommentDao;


    public LabelCommentRespDto addLabelComment(LabelComment labelComment) {
        LabelCommentRespDto respDto = new LabelCommentRespDto();

        labelCommentDao.insertLabelComment(labelComment);
        respDto.setCommentId(labelComment.getId());
        respDto.setResultCode(1);

        return respDto;
    }

    public String addLabelComment4Json(String commentJson) {
        return TaorenUtils.o2j(addLabelComment(TaorenUtils.j2o(commentJson, LabelComment.class)));
    }


    public LabelCommentRespDto delLabelComment(LabelComment labelComment) {
        LabelCommentRespDto respDto = new LabelCommentRespDto();

        if(labelComment.getId() == null){
            respDto.setResultCode(2);
            respDto.setErrorCode(2);
            respDto.setMsg("need commentId");
        }else {
            if(labelCommentDao.deleteLabelComment(labelComment) > 0){
                respDto.setResultCode(1);
            }else {
                respDto.setResultCode(2);
                respDto.setMsg("delete failure");
            }

        }

        return respDto;
    }

    public String delLabelComment4Json(String commentJson) {
        return TaorenUtils.o2j(delLabelComment(TaorenUtils.j2o(commentJson, LabelComment.class)));
    }


    public LabelCommentListRespDto getLabelComment(LabelComment labelComment) {
        LabelCommentListRespDto respDto = new LabelCommentListRespDto();
        respDto.setList(labelCommentDao.selectLabelComment(labelComment));
        respDto.setResultCode(1);
        return respDto;
    }

    public String getLabelComment4Json(String commentJson) {
        return TaorenUtils.o2j(getLabelComment(TaorenUtils.j2o(commentJson, LabelComment.class)));
    }


    public LabelCommentListRespDto labelCommentList(Map map) {
        LabelCommentListRespDto respDto = new LabelCommentListRespDto();
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


            respDto.setTotalRow(labelCommentDao.labelCommentCount(map));
            respDto.setList(labelCommentDao.labelCommentList(map));
            respDto.setResultCode(1);
        }catch (Exception e){
            e.printStackTrace();
            respDto.setResultCode(2);
        }

        return respDto;
    }

    public String labelCommentList4Json(String paramJson) {
        return TaorenUtils.o2j(labelCommentList(TaorenUtils.j2o(paramJson, HashMap.class)));
    }
}
