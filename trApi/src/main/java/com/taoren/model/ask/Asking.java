package com.taoren.model.ask;

import com.taoren.model.lb.LabelMedia;
import com.taoren.model.lb.LabelZanUser;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by wangshuisheng on 2015/5/26.
 */
public class Asking implements Serializable {

    private Long id;
    private Long uid;//所属人id
    private String askingDetail;

    private Timestamp addTime;
    private Timestamp editTime;

    private Double longitude;//发布经度
    private Double latitude;//发布纬度
    private String geohash;//经纬度hash
    private String area;//发布地区
    private Integer replyCount;//回复数量

    private List<AskingMedia> mediaList;



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

    public String getAskingDetail() {
        return askingDetail;
    }

    public void setAskingDetail(String askingDetail) {
        this.askingDetail = askingDetail;
    }

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    public Timestamp getEditTime() {
        return editTime;
    }

    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
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

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public List<AskingMedia> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<AskingMedia> mediaList) {
        this.mediaList = mediaList;
    }
}
