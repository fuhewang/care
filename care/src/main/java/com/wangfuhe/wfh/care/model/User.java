package com.wangfuhe.wfh.care.model;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wfh on 2016/11/27.
 */
@Table("user")
public class User implements Serializable {

    /**
     * id : 2
     * name : wfh
     * sid : ["04020120161013000501010000","04020120161013000401010000","04020120161013000601010000"]
     * uAsk : 1234567890
     * uPwd : qwer
     */
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int uid;
    private String id;
    private String name;
    private String uAsk;
    private String uPwd;
    private List<String> sid=new ArrayList<>();

    public User(int uid, String id, String name, String uAsk, String uPwd, List<String> sid) {
        this.uid = uid;
        this.id = id;
        this.name = name;
        this.uAsk = uAsk;
        this.uPwd = uPwd;
        this.sid.clear();
        this.sid .addAll(sid);
    }

    public User(String id, String name, String uAsk, String uPwd, List<String> sid) {
        this.id = id;
        this.name = name;

        this.uAsk = uAsk;
        this.uPwd = uPwd;
        this.sid.clear();
        this.sid .addAll(sid);
    }

    public User(int uid, String id, String name, String uAsk, String uPwd) {
        this.uid = uid;
        this.id = id;
        this.name = name;
        this.uAsk = uAsk;
        this.uPwd = uPwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUAsk() {
        return uAsk;
    }

    public void setUAsk(String uAsk) {
        this.uAsk = uAsk;
    }

    public String getUPwd() {
        return uPwd;
    }

    public void setUPwd(String uPwd) {
        this.uPwd = uPwd;
    }

    public List<String> getSid() {
        return this.sid;
    }

    public void setSid(List<String> sid) {
        if (sid!=null) {
            this.sid.clear();
            this.sid.addAll(sid);
        }
    }
}
