package com.taoren.model.user;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by wangshuisheng on 2015/7/27.
 */
public class UserReport  implements Serializable {

    private Long id;

    private Long uid;
    private Long defendant;//被告人
    private Integer reason;
    private String reasonMsg;
    private Timestamp reportTime;//用户资料修改时间
    private Timestamp editTime;//用户资料修改时间
    private Integer status;
    private Long operator;//修改人
    private Integer haveNoticed;
    private String msg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }


    public Long getDefendant() {
        return defendant;
    }

    public void setDefendant(Long defendant) {
        this.defendant = defendant;
    }

    public Integer getReason() {
        return reason;
    }

    public void setReason(Integer reason) {
        this.reason = reason;
    }

    public String getReasonMsg() {
        return reasonMsg;
    }

    public void setReasonMsg(String reasonMsg) {
        this.reasonMsg = reasonMsg;
    }

    public Timestamp getReportTime() {
        return reportTime;
    }

    public void setReportTime(Timestamp reportTime) {
        this.reportTime = reportTime;
    }

    public Timestamp getEditTime() {
        return editTime;
    }

    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getOperator() {
        return operator;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
    }

    public Integer getHaveNoticed() {
        return haveNoticed;
    }

    public void setHaveNoticed(Integer haveNoticed) {
        this.haveNoticed = haveNoticed;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
