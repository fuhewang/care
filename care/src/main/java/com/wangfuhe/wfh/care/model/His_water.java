package com.wangfuhe.wfh.care.model;

import java.io.Serializable;

/**
 * Created by wfh on 2016/12/6.
 */

public class His_water implements Serializable {


    /**
     * cond : 15.5
     * ctime : 2016-11-02 13:40:20.0
     * id : 1
     * oxy : 150.2
     * ph : 7.2
     * sid : 01020120161013000101010000
     * stime : null
     * temp : 25.5
     * turb : 1.02
     */

    private String cond;
    private String ctime;
    private int id;
    private String oxy;
    private String ph;
    private String sid;
    private Object stime;
    private double temp;
    private String turb;

    public String getCond() {
        return cond;
    }

    public void setCond(String cond) {
        this.cond = cond;
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

    public String getOxy() {
        return oxy;
    }

    public void setOxy(String oxy) {
        this.oxy = oxy;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Object getStime() {
        return stime;
    }

    public void setStime(Object stime) {
        this.stime = stime;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getTurb() {
        return turb;
    }

    public void setTurb(String turb) {
        this.turb = turb;
    }

    public His_water(String cond, int id, String ctime, String oxy,
                     String ph, String sid, Object stime, double temp, String turb) {
        this.cond = cond;
        this.id = id;
        this.ctime = ctime;
        this.oxy = oxy;
        this.ph = ph;
        this.sid = sid;
        this.stime = stime;
        this.temp = temp;
        this.turb = turb;
    }
}
