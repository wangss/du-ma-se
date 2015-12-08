package com.taoren.service.lb.model;

import com.taoren.service.user.model.GeoPointDto;

import java.io.Serializable;

/**
 * Created by wangshuisheng on 2015/6/10.
 */
public class EsLabelDto  implements Serializable{

    private String labelId;
    private String uid;
    private String labelName;
    private String labelDetail;
    private int Zan;
    private int commentCount;
    private String area;
    private String geoHash;
    private GeoPointDto location;
    private Double distance;
    private Integer positionType;


    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getLabelDetail() {
        return labelDetail;
    }

    public void setLabelDetail(String labelDetail) {
        this.labelDetail = labelDetail;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGeoHash() {
        return geoHash;
    }

    public void setGeoHash(String geoHash) {
        this.geoHash = geoHash;
    }

    public GeoPointDto getLocation() {
        return location;
    }

    public void setLocation(GeoPointDto location) {
        this.location = location;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getPositionType() {
        return positionType;
    }

    public void setPositionType(Integer positionType) {
        this.positionType = positionType;
    }
}
