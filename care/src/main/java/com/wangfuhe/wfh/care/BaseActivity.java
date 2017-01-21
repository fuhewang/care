package com.wangfuhe.wfh.care;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;
import com.wangfuhe.wfh.care.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BaseActivity extends AppCompatActivity {
    public  LiteOrm liteOrm;
    private SharedPreferences mSP;
    private SharedPreferences.Editor mEditor;
    public User mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSP=getSharedPreferences("Basesid",MODE_PRIVATE);
        mEditor=mSP.edit();
        if (liteOrm == null) {
            DataBaseConfig config = new DataBaseConfig(this, "userdb.db");
            config.debugged = true; // open the log
            config.dbVersion = 1; // set database version
            config.onUpdateListener = null; // set database update listener
            liteOrm = LiteOrm.newCascadeInstance(config);
            liteOrm.setDebugged(true);
        }
    }
    public void save(User user) {
       Log.i("wfh","保存用户人数"+liteOrm.save(user)+"") ;
    }

    public User query() {
        Log.i("wfh","当前数据库用户数"+liteOrm.queryCount(User.class)+"");
        if (liteOrm.query(User.class) != null && !liteOrm.query(User.class).isEmpty()) {
            return liteOrm.query(User.class).get(0);
        } else {
            return null;
        }

    }
    public void putsid(List<String> sid){
        Set temp=new HashSet(sid);
        mEditor.putStringSet("sid", temp);
        mEditor.commit();
    }
    public List<String> getsid(String sid){
        ArrayList<String> temp=new ArrayList<>();
        if(mSP.getStringSet(sid,null)!=null) {
            temp.addAll(mSP.getStringSet(sid, null));
            return temp;
        }
        return null;
    }
    public void delete(User user) {
        liteOrm.delete(user);
    }

    public void setlog(String msg){
        Log.i("wfh",msg);
    }
    public void setToask(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
