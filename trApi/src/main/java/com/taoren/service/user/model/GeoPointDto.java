package com.taoren.service.user.model;

import java.io.Serializable;

/**
 * Created by wangshuisheng on 2015/7/28.
 */
public class GeoPointDto implements Serializable {
    private double lat;
    private double lon;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
