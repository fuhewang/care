package com.wangfuhe.wfh.care.viewutil;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

/**
 * Created by wfh on 2016/11/27.
 */

public class DialogView {
    private Context mContext;
    private int mViewId;
    private long mExitTime;
    private View mView;

    public DialogView(Context context, int id) {
        this.mContext = context;
        this.mViewId = id;
        this.mView = LayoutInflater.from(mContext).inflate(mViewId, null);
    }

    //设置监听使得返回键dialog不消失
    private DialogInterface.OnKeyListener mKeyListener = new DialogInterface.OnKeyListener() {
        @Override
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0
                    && event.getAction() != KeyEvent.ACTION_DOWN) {
                if (System.currentTimeMillis() - mExitTime > 2000) {
                    Toast.makeText(mContext, "再按一下退出", Toast.LENGTH_SHORT).show();
                    mExitTime = System.currentTimeMillis();
                } else {
                    System.exit(0);
                }
                return true;
            } else {
                return false;
            }

        }
    };

    //获取dialog
    public AlertDialog.Builder GetDialog() {
        return new AlertDialog.Builder(mContext).setView(mView)
                .setOnKeyListener(mKeyListener)
                .setCancelable(false)
                ;
    }

    //获取dialog的界面
    public View getView() {
        return mView;
    }
}
