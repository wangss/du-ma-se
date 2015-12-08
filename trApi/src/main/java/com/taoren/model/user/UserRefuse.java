package com.taoren.model.user;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by wangshuisheng on 2015/7/27.
 */
public class UserRefuse  implements Serializable {
    private Long uid;
    private Long refuseUid;
    private Integer isRefused;
    private Timestamp addTime;//用户资料修改时间
    private String msg;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getRefuseUid() {
        return refuseUid;
    }

    public void setRefuseUid(Long refuseUid) {
        this.refuseUid = refuseUid;
    }

    public Integer getIsRefused() {
        return isRefused;
    }

    public void setIsRefused(Integer isRefused) {
        this.isRefused = isRefused;
    }

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
