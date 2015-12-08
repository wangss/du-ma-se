package com.taoren.service.user;

import com.taoren.model.user.UserReport;
import com.taoren.service.user.model.UserReportListRespDto;
import com.taoren.service.user.model.UserReportRespDto;

import java.util.Map;

/**
 * Created by wangshuisheng on 2015/5/21.
 */
public interface UserReportRemoteService {


    /**
     * 添加举报
     */
    public UserReportRespDto addUserReport(long uid, long defendant, int reason, String reasonMsg);
    public String addUserReport4Json(long uid, long defendant, int reason, String reasonMsg);

    /**
     * 查询举报
     * @param userReport
     * @return
     */
    public UserReportListRespDto getUserReport(UserReport userReport);
    public String getUserReport4Json(String paramJson);

    /**
     * 修改举报
     */
    public UserReportRespDto editUserReport(UserReport userReport);
    public String editUserReport4Json(String reportJson);


    /**
     * 分页查询report
     * @param map
     * @return
     */
    public UserReportListRespDto userReportList(Map map);
    public String userReportList4Json(String paramJson);


}
