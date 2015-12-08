package com.taoren.user.relation.dao;

import com.taoren.user.base.dao.BaseDao;
import com.taoren.model.user.UserRefuse;

import java.util.List;

/**
 * Created by wangshuisheng on 2015/7/27.
 */
public interface UserRefuseDao extends BaseDao {
    public int insertUserRefuse(UserRefuse userRefuse);

    public int deleteUserRefuse(UserRefuse userRefuse);

    public List<UserRefuse> selectUserRefuse(UserRefuse userRefuse);
}

