package com.taoren.lb.ask.dao;


import com.taoren.lb.base.dao.BaseDao;
import com.taoren.model.ask.AskingReply;

import java.util.List;
import java.util.Map;

/**
 * Created by wangshuisheng on 2015/5/27.
 */
public interface AskingReplyDao extends BaseDao {


    public int insertAskingReply(AskingReply AskingReply);
    public List<AskingReply> selectAskingReply(AskingReply AskingReply);

    public int updateAskingReply(AskingReply AskingReply);

    public int deleteAskingReplyById(Long id);
    public int deleteAskingReply(AskingReply AskingReply);


    public Integer askingReplyCount(Map map);
    public List<AskingReply> askingReplyList(Map map);


}
