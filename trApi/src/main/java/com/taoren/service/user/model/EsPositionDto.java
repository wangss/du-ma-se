package com.taoren.service.user.model;


import java.io.Serializable;
import java.util.Date;


/**
 * Created by wangshuisheng on 2015/5/22.
 */
public class EsPositionDto implements Serializable{

    private String positionId;//实际为 uid_labelId

    private String uid;//关联 User.id

    //动态变动
    private GeoPointDto location;//经纬度
    private String geohash;//经纬度hash
    private String area;//地区
    private Double distance;//距离
    private Date activeTime;//用户资料修改时间
    private Integer positionType;//1固定(label); 2移动(label); 3动态(user)


    private String labelId;
    private String labelName;
    private int Zan;
    private int commentCount;


    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public GeoPointDto getLocation() {
        return location;
    }

    public void setLocation(GeoPointDto location) {
        this.location = location;
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

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }


    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public Integer getPositionType() {
        return positionType;
    }

    public void setPositionType(Integer positionType) {
        this.positionType = positionType;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public int getZan() {
        return Zan;
    }

    public void setZan(int zan) {
        Zan = zan;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
