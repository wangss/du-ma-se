package com.taoren.service.sc.model;

import java.io.Serializable;

/**
 * Created by wangshuisheng on 2015/6/15.
 */
public class SearchParams  implements Serializable {
    private String keyword;
    private Integer gender;
    private Integer ageRange;
    private Integer lastActiveTimeRange;
    private Double lon;//
    private Double lat;//
    private Double longitude;//
    private Double latitude;//
    private Integer range;//范围 默认为20公里
    private Integer page;//第几页
    private Integer pageSize;//默认为20


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(Integer ageRange) {
        this.ageRange = ageRange;
    }

    public Integer getLastActiveTimeRange() {
        return lastActiveTimeRange;
    }

    public void setLastActiveTimeRange(Integer lastActiveTimeRange) {
        this.lastActiveTimeRange = lastActiveTimeRange;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
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

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
