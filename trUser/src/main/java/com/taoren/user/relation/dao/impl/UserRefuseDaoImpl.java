package com.taoren.user.relation.dao.impl;

import com.taoren.user.base.dao.impl.BaseDaoImpl;
import com.taoren.model.user.UserRefuse;
import com.taoren.user.relation.dao.UserRefuseDao;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangshuisheng on 2015/7/27.
 */
@Repository
public class UserRefuseDaoImpl extends BaseDaoImpl implements UserRefuseDao {

    @Override
    protected String getNameSpace() {
        return "UserRefuseMapper.";
    }

    @Autowired
    SqlSession sqlSession;

    public int insertUserRefuse(UserRefuse userRefuse) {
        return sqlSession.insert(getNameSpace() + "insertUserRefuse", userRefuse);
    }

    public int deleteUserRefuse(UserRefuse userRefuse) {
        return sqlSession.delete(getNameSpace() + "deleteUserRefuse", userRefuse);
    }

    public List<UserRefuse> selectUserRefuse(UserRefuse userRefuse) {
        return sqlSession.selectList(getNameSpace() + "selectUserRefuse", userRefuse);
    }
}
