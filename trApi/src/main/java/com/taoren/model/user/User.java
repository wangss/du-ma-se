package com.taoren.model.user;

import com.alibaba.fastjson.annotation.JSONField;
import com.taoren.common.constant.Constants;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by wangshuisheng on 2015/5/21.
 *
 * 用户信息表
 */
public class User implements Serializable {

    private static final long serialVersionUID = 4049911301291426632L;

    private Long id;
    private String trId;//淘人id
    private String phone;//手机
    private Integer areaCode;//国际区号
    private String registerPhone;//注册手机
    private String password;
    private String passwordNew;//新密码，只是修改密码时会用，数据库不存
    private String verifyCode;//手机验证码

//    @JSONField(format = Constants.DATE_TIME_FORMAT)
    private Timestamp createTime;//创建帐户时间

    private Integer deviceType;//注册时设备类型 1IOS;2Android;3
    private String uuid;//注册设备uuid
    private String ip;//注册时ip

    private String token;//用户验证token
    private String sign;//签名

    private String deviceToken;//消息推送token
    private String netWorkType;//网络制式 0无; 1wifi; 22G; 3 3G; 4 4G; 9移动（包括2G 3G 4G）


    private Double longitude;//注册时经度
    private Double latitude;//注册时纬度
    private String geohash;//经纬度hash
    private String area;//注册地区


    private Integer status;//账号状态 1正常使用;2账号注销; 3强制禁用;
    private String statusNote;//状态说明
    private Integer easemobStatus;//对应的环信用户生成状态 1已创建 0未创建

//    @JSONField(format = Constants.DATE_TIME_FORMAT)
    private Timestamp accountUpdateTime;//帐户信息修改时间
    private String accountUpdateMsg;//帐户信息修改msg

    private Integer userType;//用户类型 1普通用户 2公司运营人员


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrId() {
        return trId;
    }

    public void setTrId(String trId) {
        this.trId = trId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }

    public String getRegisterPhone() {
        return registerPhone;
    }

    public void setRegisterPhone(String registerPhone) {
        this.registerPhone = registerPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordNew() {
        return passwordNew;
    }

    public void setPasswordNew(String passwordNew) {
        this.passwordNew = passwordNew;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getNetWorkType() {
        return netWorkType;
    }

    public void setNetWorkType(String netWorkType) {
        this.netWorkType = netWorkType;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getGeohash() {
        return geohash;
    }

    public void setGeohash(String geohash) {
        this.geohash = geohash;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusNote() {
        return statusNote;
    }

    public void setStatusNote(String statusNote) {
        this.statusNote = statusNote;
    }

    public Integer getEasemobStatus() {
        return easemobStatus;
    }

    public void setEasemobStatus(Integer easemobStatus) {
        this.easemobStatus = easemobStatus;
    }

    public Timestamp getAccountUpdateTime() {
        return accountUpdateTime;
    }

    public void setAccountUpdateTime(Timestamp accountUpdateTime) {
        this.accountUpdateTime = accountUpdateTime;
    }

    public String getAccountUpdateMsg() {
        return accountUpdateMsg;
    }

    public void setAccountUpdateMsg(String accountUpdateMsg) {
        this.accountUpdateMsg = accountUpdateMsg;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}

