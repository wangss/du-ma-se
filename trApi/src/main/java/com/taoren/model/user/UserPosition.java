package com.taoren.model.user;

import com.alibaba.fastjson.annotation.JSONField;
import com.taoren.common.constant.Constants;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


/**
 * Created by wangshuisheng on 2015/5/22.
 */
public class UserPosition implements Serializable {
    private Long uid;//关联 User.id

    private Double longitude;//注册时经度
    private Double latitude;//注册时纬度
    private String geohash;//经纬度hash
    private String area;//注册地区
    private Timestamp activeTime;//用户资料修改时间


    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
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

    public Timestamp getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Timestamp activeTime) {
        this.activeTime = activeTime;
    }

}
