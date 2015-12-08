package com.taoren.user.relation.dao;

import com.taoren.user.base.dao.BaseDao;
import com.taoren.model.user.UserFriend;

import java.util.List;

/**
 * Created by wangshuisheng on 2015/7/27.
 */
public interface UserFriendDao extends BaseDao {
    public int insertUserFriend(UserFriend userFriend);

    public int deleteUserFriend(UserFriend userFriend);

    public List<UserFriend> selectUserFriendIds(UserFriend userFriend);
}

