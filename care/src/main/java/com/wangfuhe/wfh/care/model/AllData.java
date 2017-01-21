package com.wangfuhe.wfh.care.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wfh on 2016/12/6.
 */

public class AllData implements Serializable {

    private List<His_oxy> hisOxies;
    /**
     * ctime : 2016-12-06 21:25:43.0
     * id : 18763
     * sid : 04020120161013000501010000
     * svalue : 51.4
     */

    private List<His_sound> hisSounds;
    private List<His_water> hisWaters;
    /**
     * id : 15826
     * location : 1
     * soilHumidity : 0
     * soilTemperature : 21.3
     * tIMESTAMP : 2016-12-06 21:34:54.0
     */

    private List<Luguhusoil> luguhusoils;
    /**
     * baro : 746.4
     * batt : 11.83
     * dew : -2.1
     * hum : 60
     * id : 1935668
     * location : 1
     * rainf : 0
     * sr_sum : 3544000
     * srad : 1636
     * t_air : 5.1
     * timestamp : 2016-12-06 21:25:23.0
     * wdir : 347
     * ws_max : 1.4
     * wspd : 0
     */

    private List<Luguhuwind> luguhuwinds;

    public List<His_oxy> getHisOxies() {
        return hisOxies;
    }

    public void setHisOxies(List<His_oxy> hisOxies) {
        this.hisOxies = hisOxies;
    }

    public List<His_sound> getHisSounds() {
        return hisSounds;
    }

    public void setHisSounds(List<His_sound> hisSounds) {
        this.hisSounds = hisSounds;
    }

    public List<His_water> getHisWaters() {
        return hisWaters;
    }

    public void setHisWaters(List<His_water> hisWaters) {
        this.hisWaters = hisWaters;
    }

    public List<Luguhusoil> getLuguhusoils() {
        return luguhusoils;
    }

    public void setLuguhusoils(List<Luguhusoil> luguhusoils) {
        this.luguhusoils = luguhusoils;
    }

    public List<Luguhuwind> getLuguhuwinds() {
        return luguhuwinds;
    }

    public void setLuguhuwinds(List<Luguhuwind> luguhuwinds) {
        this.luguhuwinds = luguhuwinds;
    }
}
