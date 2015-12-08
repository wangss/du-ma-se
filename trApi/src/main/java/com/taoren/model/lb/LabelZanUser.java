package com.taoren.model.lb;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by wangshuisheng on 2015/7/2.
 */
public class LabelZanUser implements Serializable {
    private Long uid;
    private Long labelId;
    private String avatar;
    private String nickname;
    private String signature;//个性签名
    private Integer gender;//性别 1男;2女;3保密



    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
