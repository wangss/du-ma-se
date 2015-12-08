package com.taoren.service.user.model;

import com.taoren.model.user.User;
import com.taoren.service.base.model.BaseRespDto;

/**
 * Created by Administrator on 2015/5/21.
 */
public class UserRespDto extends BaseRespDto {

    private Long uid;
    private String token;
    private User user;


    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
