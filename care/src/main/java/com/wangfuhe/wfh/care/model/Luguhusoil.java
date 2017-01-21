package com.wangfuhe.wfh.care.model;

import java.io.Serializable;

/**
 * Created by wfh on 2016/12/6.
 */

public class Luguhusoil implements Serializable {

    /**
     * id : 15815
     * location : 1
     * soilHumidity : 0
     * soilTemperature : 21.7
     * tIMESTAMP : 2016-12-06 18:50:19.0
     */

    private int id;
    private String location;
    private int soilHumidity;
    private double soilTemperature;
    private String tIMESTAMP;

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

    public int getSoilHumidity() {
        return soilHumidity;
    }

    public void setSoilHumidity(int soilHumidity) {
        this.soilHumidity = soilHumidity;
    }

    public double getSoilTemperature() {
        return soilTemperature;
    }

    public void setSoilTemperature(double soilTemperature) {
        this.soilTemperature = soilTemperature;
    }

    public String getTIMESTAMP() {
        return tIMESTAMP;
    }

    public void setTIMESTAMP(String tIMESTAMP) {
        this.tIMESTAMP = tIMESTAMP;
    }

    public Luguhusoil(int id, String location, int soilHumidity,
                      double soilTemperature, String tIMESTAMP) {
        this.id = id;
        this.location = location;
        this.soilHumidity = soilHumidity;
        this.soilTemperature = soilTemperature;
        this.tIMESTAMP = tIMESTAMP;
    }
}
