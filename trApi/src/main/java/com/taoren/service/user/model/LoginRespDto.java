package com.taoren.service.user.model;

import com.taoren.service.base.model.BaseRespDto;
import com.taoren.model.user.UserInfo;

/**
 * Created by Administrator on 2015/5/21.
 */
public class LoginRespDto extends BaseRespDto {

    private UserInfo userInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
