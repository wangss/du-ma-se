package com.taoren.service.user.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.taoren.common.constant.Constants;
import com.taoren.service.lb.model.EsLabelDto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by wangshuisheng on 2015/6/10.
 */
public class EsUserDto implements Serializable {

    private String uid;

    private String trId;

    private String nickname;

    @JSONField(format = Constants.DATE_FORMAT)
    private Date birthday;

    private Integer gender;//性别

    private String avatar;//头像

    private String signature;//个性签名

    private String area;//位置

    private String geoHash;//geoHash

    private GeoPointDto location;//经纬度

    private Double distance;

    private Integer privacyFind;//1能找到我，2别人找不到我

    private Date activeTime;//最后活跃时间

    private List<EsLabelDto> labelList;

    public EsUserDto() {
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTrId() {
        return trId;
    }

    public void setTrId(String trId) {
        this.trId = trId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
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

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public List<EsLabelDto> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<EsLabelDto> labelList) {
        this.labelList = labelList;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getPrivacyFind() {
        return privacyFind;
    }

    public void setPrivacyFind(Integer privacyFind) {
        this.privacyFind = privacyFind;
    }
}
