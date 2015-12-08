package com.taoren.service.user.model;

import com.taoren.service.base.model.BaseRespDto;

/**
 * Created by Administrator on 2015/5/21.
 */
public class NewUserRespDto extends BaseRespDto {

    private Long uid;//用户id
    private String token;


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

}
