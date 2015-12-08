package com.taoren.service.user.model;

import com.taoren.service.base.model.BaseRespDto;
import com.taoren.model.user.UserReport;

import java.util.List;

/**
 * Created by Administrator on 2015/5/21.
 */
public class UserReportRespDto extends BaseRespDto {
    private UserReport userReport;

    public UserReport getUserReport() {
        return userReport;
    }

    public void setUserReport(UserReport userReport) {
        this.userReport = userReport;
    }
}
