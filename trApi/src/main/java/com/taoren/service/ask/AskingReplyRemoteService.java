package com.taoren.service.ask;

import com.taoren.model.ask.AskingReply;
import com.taoren.service.ask.model.AskingReplyListRespDto;
import com.taoren.service.ask.model.AskingReplyRespDto;

import java.util.Map;

/**
 * Created by wangshuisheng on 2015/5/26.
 */
public interface AskingReplyRemoteService {
    /**
     * 增加askingReply
     */
    public AskingReplyRespDto addAskingReply(AskingReply reply);
    public String addAskingReply4Json(String replyJson);


    /**
     * 删除
     */

    public AskingReplyRespDto delAskingReply(AskingReply reply);
    public String delAskingReply4Json(String replyJson);

    /**
     * 获取
     */
    public AskingReplyListRespDto getAskingReply(AskingReply reply);
    public String getAskingReply4Json(String replyJson);


    /**
     * 分页
     * @param map
     * @return
     */
    public AskingReplyListRespDto askingReplyList(Map map);
    public String askingReplyList4Json(String paramJson);

}
