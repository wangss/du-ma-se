package com.taoren.sc.esuser.dao.impl;


import com.taoren.sc.base.dao.impl.BaseDaoImpl;
import com.taoren.sc.esuser.dao.UserInfoDao;
import com.taoren.model.user.UserInfo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangshuisheng on 2015/5/27.
 */
@Repository
public class UserInfoDaoImpl extends BaseDaoImpl implements UserInfoDao {

    @Override
    protected String getNameSpace() {
        return "EsUserMapper.";
    }

    @Autowired
    SqlSession sqlSession;


    public UserInfo selectUserInfoById(Long id) {
        return sqlSession.selectOne(getNameSpace() + "selectUserInfoById", id);
    }

    public List<UserInfo> selectUserInfo(UserInfo userInfo) {
        return sqlSession.selectList(getNameSpace() + "selectUserInfo", userInfo);
    }
}
