package com.wangfuhe.wfh.care.model;

import java.io.Serializable;

/**
 * Created by wfh on 2016/11/28.
 */

public class Base_sound implements Serializable{

    /**
     * ctime : 2016-11-15 09:57:52.0
     * id : 1
     * lat : 27.681902
     * loc : 大洛水
     * lon : 100.77289
     * sid : 04020120161013000501010000
     * sname : 大洛水噪声
     */

    private String ctime;
    private int id;
    private double lat;
    private String loc;
    private double lon;
    private String sid;
    private String sname;

    public Base_sound(String ctime, int id, double lat, String loc, double lon, String sid, String sname) {
        this.ctime = ctime;
        this.id = id;
        this.lat = lat;
        this.loc = loc;
        this.lon = lon;
        this.sid = sid;
        this.sname = sname;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
}
