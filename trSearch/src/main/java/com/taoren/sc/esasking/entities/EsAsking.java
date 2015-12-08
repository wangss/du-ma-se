package com.taoren.sc.esasking.entities;

import com.taoren.sc.constants.IndexTypes;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.Date;


/**
 * Created by wangshuisheng on 2015/5/22.
 */
@Document(indexName = IndexTypes.TAOREN_INDEX, type = IndexTypes.TAOREN_TYPE_ASKING)

public class EsAsking {

    @Id
    private String askingId;//

    @Field(type = FieldType.String, store = true)
    @Parent(type = IndexTypes.TAOREN_TYPE_ASKING_PARENT)
    private String uid;//关联 User.id
    private String askingDetail;

    private GeoPoint location;//经纬度
    private String geohash;//经纬度hash
    private String area;//地区
    private Double distance;//距离
    private Date activeTime;//发布时间


    private int replyCount;


    public String getAskingId() {
        return askingId;
    }

    public void setAskingId(String askingId) {
        this.askingId = askingId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAskingDetail() {
        return askingDetail;
    }

    public void setAskingDetail(String askingDetail) {
        this.askingDetail = askingDetail;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
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

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }
}
