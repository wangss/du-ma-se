package com.taoren.lb.label.dao;

import com.taoren.lb.base.dao.BaseDao;
import com.taoren.model.user.User;

/**
 * Created by wangshuisheng on 2015/5/27.
 */
public interface UserDao extends BaseDao{

    public User getTokenUser(String token);

}
