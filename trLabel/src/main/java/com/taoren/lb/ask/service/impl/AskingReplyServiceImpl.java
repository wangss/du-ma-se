package com.taoren.lb.ask.service.impl;

import com.taoren.common.util.TaorenUtils;
import com.taoren.lb.ask.dao.AskingReplyDao;
import com.taoren.lb.label.dao.LabelDao;
import com.taoren.lb.label.dao.LabelMediaDao;
import com.taoren.lb.label.dao.LabelZanDao;
import com.taoren.model.ask.AskingReply;
import com.taoren.model.lb.LabelComment;
import com.taoren.service.ask.AskingReplyRemoteService;
import com.taoren.service.ask.model.AskingReplyListRespDto;
import com.taoren.service.ask.model.AskingReplyRespDto;
import com.taoren.service.lb.LabelCommentRemoteService;
import com.taoren.service.lb.model.LabelCommentListRespDto;
import com.taoren.service.lb.model.LabelCommentRespDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2015/5/25.
 */
@Service
public class AskingReplyServiceImpl implements AskingReplyRemoteService {

    Logger logger = LoggerFactory.getLogger(AskingReplyServiceImpl.class);

    @Autowired
    private AskingReplyDao askingReplyDao;

    public AskingReplyRespDto addAskingReply(AskingReply reply) {
        AskingReplyRespDto respDto = new AskingReplyRespDto();

        askingReplyDao.insertAskingReply(reply);
        respDto.setReplyId(reply.getId());
        respDto.setResultCode(1);
        respDto.setMsg("Add success");
        return respDto;
    }

    public String addAskingReply4Json(String replyJson) {
        return TaorenUtils.o2j(addAskingReply(TaorenUtils.j2o(replyJson, AskingReply.class)));
    }

    public AskingReplyRespDto delAskingReply(AskingReply reply) {
        AskingReplyRespDto respDto = new AskingReplyRespDto();

        if(reply.getId() == null){
            respDto.setResultCode(2);
            respDto.setErrorCode(2);
            respDto.setMsg("need commentId");
        }else {
            if(askingReplyDao.deleteAskingReply(reply) > 0){
                respDto.setResultCode(1);
            }else {
                respDto.setResultCode(2);
                respDto.setMsg("delete failure");
            }

        }

        return respDto;
    }

    public String delAskingReply4Json(String replyJson) {
        return TaorenUtils.o2j(delAskingReply(TaorenUtils.j2o(replyJson, AskingReply.class)));

    }

    public AskingReplyListRespDto getAskingReply(AskingReply reply) {
        AskingReplyListRespDto respDto = new AskingReplyListRespDto();
        respDto.setList(askingReplyDao.selectAskingReply(reply));
        respDto.setResultCode(1);
        return respDto;
    }

    public String getAskingReply4Json(String replyJson) {
        return TaorenUtils.o2j(getAskingReply(TaorenUtils.j2o(replyJson, AskingReply.class)));
    }

    public AskingReplyListRespDto askingReplyList(Map map) {
        AskingReplyListRespDto respDto = new AskingReplyListRespDto();
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


            respDto.setTotalRow(askingReplyDao.askingReplyCount(map));
            respDto.setList(askingReplyDao.askingReplyList(map));
            respDto.setResultCode(1);
        }catch (Exception e){
            e.printStackTrace();
            respDto.setResultCode(2);
        }

        return respDto;
    }

    public String askingReplyList4Json(String paramJson) {
        return TaorenUtils.o2j(askingReplyList(
                TaorenUtils.j2o(paramJson, HashMap.class)));
    }
}
