package com.taoren.service.base.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.taoren.common.constant.CodeMsgConstants;
import com.taoren.common.constant.Constants;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wangshuisheng on 2015/6/9.
 */
public class BaseRespDto implements Serializable {
    protected Integer resultCode;//1成功; 2失败
    protected Integer errorCode;// 如果resultCode = 2, 则
    protected String msg;

    @JSONField(format = Constants.DATE_TIME_FORMAT)
    protected Date time;//如果成功，则为发送验证码时间

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public BaseRespDto() {
    }

    public BaseRespDto(Integer resultCode, String msg) {
        this.resultCode = resultCode;
        this.msg = msg;
    }
}
