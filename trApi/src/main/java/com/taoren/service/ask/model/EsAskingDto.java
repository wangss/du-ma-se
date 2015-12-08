package com.taoren.service.ask.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.taoren.common.constant.Constants;
import com.taoren.model.ask.AskingMedia;
import com.taoren.service.user.model.GeoPointDto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by wangshuisheng on 2015/6/10.
 */
public class EsAskingDto implements Serializable {

    private Long id;
    private Long askingId;
    private Long uid;//所属人id
    private String nickname;
    private String avatar;


    @JSONField(format = Constants.DATE_FORMAT)
    private Date birthday;

    private String askingDetail;

    private Timestamp addTime;


    private GeoPointDto location;
    private String geohash;//经纬度hash
    private String area;//发布地区
    private Double distance;
    private Integer replyCount;//回复数量

    private List<AskingMedia> mediaList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAskingId() {
        return askingId;
    }

    public void setAskingId(Long askingId) {
        this.askingId = askingId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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
