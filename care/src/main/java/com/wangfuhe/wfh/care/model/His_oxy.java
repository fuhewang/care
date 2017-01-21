package com.wangfuhe.wfh.care.model;

import java.io.Serializable;

/**
 * Created by wfh on 2016/12/6.
 */

public class His_oxy implements Serializable {

    /**
     * ctime : 2016-11-22 17:57:59.603
     * id : 76
     * sid : 2020220161122000101010000
     * svalue : 15
     */

    private String ctime;
    private int id;
    private String sid;
    private int svalue;

    public His_oxy(String ctime, int id, String sid, int svalue) {
        this.ctime = ctime;
        this.id = id;
        this.sid = sid;
        this.svalue = svalue;
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

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public int getSvalue() {
        return svalue;
    }

    public void setSvalue(int svalue) {
        this.svalue = svalue;
    }
}
