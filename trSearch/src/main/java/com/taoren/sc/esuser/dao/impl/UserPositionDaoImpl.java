package com.taoren.sc.esuser.dao.impl;

import com.taoren.sc.base.dao.impl.BaseDaoImpl;
import com.taoren.sc.esuser.dao.UserPositionDao;
import com.taoren.model.user.UserPosition;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangshuisheng on 2015/6/11.
 */
@Repository
public class UserPositionDaoImpl extends BaseDaoImpl implements UserPositionDao {

    @Autowired
    SqlSession sqlSession;

    @Override
    protected String getNameSpace() {
        return "UserPositionMapper.";
    }

    public int insertUserPosition(UserPosition userPosition) {
        return sqlSession.update(getNameSpace() + "insertUserPosition", userPosition);
    }

    public int updateUserPosition(UserPosition userPosition) {
        return sqlSession.update(getNameSpace() + "updateUserPosition", userPosition);
    }

    public UserPosition selectUserPositionByUid(Long id) {
        return sqlSession.selectOne(getNameSpace() + "selectUserPositionByUid", id);
    }

    public List<UserPosition> selectUserPosition(UserPosition userPosition) {
        return sqlSession.selectList(getNameSpace() + "selectUserPosition", userPosition);
    }


    public int saveUserPosition(UserPosition userPosition) {
        int i = updateUserPosition(userPosition);
        if( i <= 0){
            i = insertUserPosition(userPosition);
        }
        return i;
    }


}
