package com.taoren.user.relation.dao;

import com.taoren.user.base.dao.BaseDao;
import com.taoren.model.user.UserReport;

import java.util.List;
import java.util.Map;

/**
 * Created by wangshuisheng on 2015/7/27.
 */
public interface UserReportDao extends BaseDao {

    public int insertUserReport(UserReport userReport);

    public int updateUserReport(UserReport userReport);


    public Integer userReportCount(Map map);
    public List<UserReport> userReportList(Map map);

    public List<UserReport> selectUserReport(UserReport userReport);

}
