package com.taoren.service.user.model;

import com.taoren.service.base.model.BaseRespDto;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/5/21.
 */
public class QiniuToken  implements Serializable {

    private String key;
    private String token;

    public QiniuToken() {
    }

    public QiniuToken(String key, String token) {
        this.key = key;
        this.token = token;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
