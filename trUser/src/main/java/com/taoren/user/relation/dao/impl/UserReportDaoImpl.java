package com.taoren.user.relation.dao.impl;

import com.taoren.user.base.dao.impl.BaseDaoImpl;
import com.taoren.model.user.UserReport;
import com.taoren.user.relation.dao.UserReportDao;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wangshuisheng on 2015/7/27.
 */
@Repository
public class UserReportDaoImpl  extends BaseDaoImpl implements UserReportDao {
    @Override
    protected String getNameSpace() {
        return "UserReportMapper.";
    }

    @Autowired
    SqlSession sqlSession;

    public int insertUserReport(UserReport userReport) {
        return sqlSession.insert(getNameSpace() + "insertUserReport", userReport);
    }

    public int updateUserReport(UserReport userReport) {
        return sqlSession.update(getNameSpace() + "updateUserReport", userReport);
    }

    public Integer userReportCount(Map map) {
        return sqlSession.selectOne(getNameSpace() + "userReportCount", map);
    }

    public List<UserReport> userReportList(Map map) {
        return sqlSession.selectList(getNameSpace() + "userReportList", map);
    }

    public List<UserReport> selectUserReport(UserReport userReport) {
        return sqlSession.selectList(getNameSpace() + "selectUserReport", userReport);
    }
}
