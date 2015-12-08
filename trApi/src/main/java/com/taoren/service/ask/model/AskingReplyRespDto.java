package com.taoren.service.ask.model;

import com.taoren.model.ask.AskingReply;
import com.taoren.service.base.model.BaseRespDto;

/**
 * Created by Administrator on 2015/5/21.
 */
public class AskingReplyRespDto extends BaseRespDto{

    private Long replyId;
    private AskingReply askingReply;


    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public AskingReply getAskingReply() {
        return askingReply;
    }

    public void setAskingReply(AskingReply askingReply) {
        this.askingReply = askingReply;
    }
}
