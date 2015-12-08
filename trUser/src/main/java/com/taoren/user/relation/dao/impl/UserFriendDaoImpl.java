package com.taoren.user.relation.dao.impl;

import com.taoren.user.base.dao.impl.BaseDaoImpl;
import com.taoren.model.user.UserFriend;
import com.taoren.user.relation.dao.UserFriendDao;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangshuisheng on 2015/7/27.
 */
@Repository
public class UserFriendDaoImpl extends BaseDaoImpl implements UserFriendDao  {

    @Override
    protected String getNameSpace() {
        return "UserFriendMapper.";
    }

    @Autowired
    SqlSession sqlSession;

    public int insertUserFriend(UserFriend userFriend) {
        return sqlSession.insert(getNameSpace() + "insertUserFriend", userFriend);
    }

    public int deleteUserFriend(UserFriend userFriend) {
        return sqlSession.delete(getNameSpace() + "deleteUserFriend", userFriend);
    }

    public List<UserFriend> selectUserFriendIds(UserFriend userFriend) {
        return sqlSession.selectList(getNameSpace() + "selectUserFriendIds", userFriend);
    }
}
