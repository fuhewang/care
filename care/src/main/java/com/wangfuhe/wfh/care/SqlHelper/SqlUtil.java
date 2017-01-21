package com.wangfuhe.wfh.care.SqlHelper;

import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;
import com.wangfuhe.wfh.care.model.User;

/**
 * Created by wfh on 2016/11/27.
 */

public class SqlUtil {
    public static LiteOrm liteOrm;

    public static SqlUtil CreateDd(Context context, String dbname) {
        DataBaseConfig config = new DataBaseConfig(context, dbname + ".db");
        config.debugged = true; // open the log
        config.dbVersion = 1; // set database version
        config.onUpdateListener = null; // set database update listener
        liteOrm = LiteOrm.newSingleInstance(config);
        return new SqlUtil();
    }

    public void save(User user) {
        liteOrm.save(user);
    }

    public User query() {
        if (liteOrm.query(User.class) != null && !liteOrm.query(User.class).isEmpty()) {
            return liteOrm.query(User.class).get(0);
        } else {
            return null;
        }

    }

    public void delete(User user) {
        liteOrm.delete(user);
    }
}
