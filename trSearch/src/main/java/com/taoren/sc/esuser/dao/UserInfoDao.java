package com.taoren.sc.esuser.dao;
import com.taoren.sc.base.dao.BaseDao;
import com.taoren.model.user.UserInfo;

import java.util.List;

/**
 * Created by wangshuisheng on 2015/5/27.
 */
public interface UserInfoDao extends BaseDao {


    public UserInfo selectUserInfoById(Long id);
    public List<UserInfo> selectUserInfo(UserInfo userInfo);
}
