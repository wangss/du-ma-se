package com.taoren.user.account.dao;

import com.taoren.model.user.User;
import com.taoren.model.user.UserInfo;
import com.taoren.user.base.dao.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * Created by wangshuisheng on 2015/5/27.
 */
public interface MessageDao extends BaseDao{

    public void pushUserMessage(long uid, String msg);
    public String popUserMessage(long uid);

    public long userMessageSize(long uid);

}
