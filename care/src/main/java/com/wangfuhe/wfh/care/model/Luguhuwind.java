package com.wangfuhe.wfh.care.model;

/**
 * Created by wfh on 2016/12/6.
 */

public class Luguhuwind {

    /**
     * baro : 745.6
     * batt : 11.83
     * dew : -3.1
     * hum : 45
     * id : 1935632
     * location : 1
     * rainf : 0
     * sr_sum : 3078000
     * srad : 1416
     * t_air : 8.2
     * timestamp : 2016-12-06 18:25:20.0
     * wdir : 343
     * ws_max : 1.5
     * wspd : 0
     */

    private double baro;
    private double batt;
    private double dew;
    private int hum;
    private int id;
    private String location;
    private int rainf;
    private int sr_sum;
    private int srad;
    private double t_air;
    private String timestamp;
    private int wdir;
    private double ws_max;
    private double wspd;

    public double getBaro() {
        return baro;
    }

    public void setBaro(double baro) {
        this.baro = baro;
    }

    public double getBatt() {
        return batt;
    }

    public void setBatt(double batt) {
        this.batt = batt;
    }

    public double getDew() {
        return dew;
    }

    public void setDew(double dew) {
        this.dew = dew;
    }

    public int getHum() {
        return hum;
    }

    public void setHum(int hum) {
        this.hum = hum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getRainf() {
        return rainf;
    }

    public void setRainf(int rainf) {
        this.rainf = rainf;
    }

    public int getSr_sum() {
        return sr_sum;
    }

    public void setSr_sum(int sr_sum) {
        this.sr_sum = sr_sum;
    }

    public int getSrad() {
        return srad;
    }

    public void setSrad(int srad) {
        this.srad = srad;
    }

    public double getT_air() {
        return t_air;
    }

    public void setT_air(double t_air) {
        this.t_air = t_air;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getWdir() {
        return wdir;
    }

    public void setWdir(int wdir) {
        this.wdir = wdir;
    }

    public double getWs_max() {
        return ws_max;
    }

    public void setWs_max(double ws_max) {
        this.ws_max = ws_max;
    }

    public double getWspd() {
        return wspd;
    }

    public void setWspd(double wspd) {
        this.wspd = wspd;
    }

    public Luguhuwind(double baro, double ws_max, int wspd, int wdir, String timestamp, double t_air, int srad,
                      int sr_sum, int rainf, String location,
                      int id, int hum, double dew, double batt) {
        this.baro = baro;
        this.ws_max = ws_max;
        this.wspd = wspd;
        this.wdir = wdir;
        this.timestamp = timestamp;
        this.t_air = t_air;
        this.srad = srad;
        this.sr_sum = sr_sum;
        this.rainf = rainf;
        this.location = location;
        this.id = id;
        this.hum = hum;
        this.dew = dew;
        this.batt = batt;
    }
}
