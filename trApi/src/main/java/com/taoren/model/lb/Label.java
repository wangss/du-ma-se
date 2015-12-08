package com.taoren.model.lb;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by wangshuisheng on 2015/5/26.
 */
public class Label implements Serializable {

    private Long id;
    private Long uid;//所属人id
    private String labelName;
    private String labelDetail;

    private Timestamp addTime;
    private Timestamp editTime;
    private Double longitude;//发布经度
    private Double latitude;//发布纬度
    private String geohash;//经纬度hash
    private String area;//发布地区
    private Integer positionType;//坐标类型 1固定 2移动
    private Integer deadlineType;//有效时间类型 1永久 2时间段
    private Timestamp startTime;//有效开始时间
    private Timestamp endTime;//有效结束时间

    private Integer zan;//赞数量
    private Integer commentCount;//评论数

    private boolean haveZan;//是否已赞
    private Long zanUid;

    private String msg;//msg
    private Integer status;//标签状态 1创建 2生效 3失效 4废除

    private List<LabelMedia> mediaList;
    private List<LabelZanUser> zanUserList;




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

    public Integer getDeadlineType() {
        return deadlineType;
    }

    public void setDeadlineType(Integer deadlineType) {
        this.deadlineType = deadlineType;
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

    public Integer getPositionType() {
        return positionType;
    }

    public void setPositionType(Integer positionType) {
        this.positionType = positionType;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Integer getZan() {
        return zan;
    }

    public void setZan(Integer zan) {
        this.zan = zan;
    }

    public boolean isHaveZan() {
        return haveZan;
    }

    public void setHaveZan(boolean haveZan) {
        this.haveZan = haveZan;
    }

    public Long getZanUid() {
        return zanUid;
    }

    public void setZanUid(Long zanUid) {
        this.zanUid = zanUid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public List<LabelMedia> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<LabelMedia> mediaList) {
        this.mediaList = mediaList;
    }

    public List<LabelZanUser> getZanUserList() {
        return zanUserList;
    }

    public void setZanUserList(List<LabelZanUser> zanUserList) {
        this.zanUserList = zanUserList;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
}
