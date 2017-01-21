package com.wangfuhe.wfh.care.model;

import java.io.Serializable;

/**
 * Created by wfh on 2016/11/29.
 */

public class His_sound implements Serializable {

    /**
     * ctime : 2024-05-07 11:23:28.0
     * id : 2237
     * sid : 04020120161013000501010000
     * svalue : 54.5
     */

    private String ctime;
    private int id;
    private String sid;
    private double svalue;

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

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public double getSvalue() {
        return svalue;
    }

    public void setSvalue(double svalue) {
        this.svalue = svalue;
    }

    public His_sound(String ctime, int id, String sid, double svalue) {
        this.ctime = ctime;
        this.id = id;
        this.sid = sid;
        this.svalue = svalue;
    }
}
