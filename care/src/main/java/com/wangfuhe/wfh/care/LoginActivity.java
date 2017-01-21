package com.wangfuhe.wfh.care;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wangfuhe.wfh.care.ResultBean.SCode;
import com.wangfuhe.wfh.care.ViewActivity.MapActivity;
import com.wangfuhe.wfh.care.model.User;
import com.wangfuhe.wfh.care.networkapi.UserApi;
import com.wangfuhe.wfh.care.networkutil.NetWorkTool;
import com.wangfuhe.wfh.care.viewutil.DialogView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {
    private Context mContext;
    private DialogView mDialogView;
    private View mDialog;
    private EditText mUserName;
    private EditText mUserPwd;
    private UserApi service;
    private ProgressDialog mProgressDialog;
    private String mAsk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.wangfuhe.wfh.care.R.layout.activity_login);
        mContext = this;
        initRetroift();
        initview();//初始化界面

    }

    //进行网络请求获取用户信息
    private void initRetroift() {
        service = NetWorkTool.GetUserService();
    }

    private void getuserinfo(String ask) {

        Call<User> call = service.PostRequest(ask);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mUser = response.body();
                    showmodify(mUser);
                    mProgressDialog.dismiss();
                } else {
                    Toast.makeText(mContext, "请求码不存在", Toast.LENGTH_SHORT).show();
                    loginreq();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    //用户登入界面
    private void initview() {
        mUser = query();
        if (mUser == null) {
            mDialogView = new DialogView(this, com.wangfuhe.wfh.care.R.layout.login_modify);
            mDialog = mDialogView.getView();
            TextView mtitle = (TextView) mDialog.findViewById(com.wangfuhe.wfh.care.R.id.titlemessage);
            mUserName = (EditText) mDialog.findViewById(com.wangfuhe.wfh.care.R.id.vt_user_name);
            mUserPwd = (EditText) mDialog.findViewById(com.wangfuhe.wfh.care.R.id.vt_user_pwd);
            mtitle.setText("登入界面");
            mDialogView.GetDialog().setPositiveButton("登入", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    loginuser(mUserName.getText().toString(), mUserPwd.getText().toString());
                }
            }).setNeutralButton("请求", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    loginreq();
                }
            }).setNegativeButton("退出", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            }).show();
        } else {
            mUser.setSid(getsid("sid"));
            if (!mUser.getSid().isEmpty()) {
                Intent it = new Intent(LoginActivity.this, MapActivity.class);
                startActivity(it);
                finish();
            }
        }
    }

    //用户登入
    private void loginuser(final String name, final String pwd) {
        Call<User> call = service.loginResult(name, pwd);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mUser = response.body();
                    if (mUser != null) {
                        save(mUser);
                        putsid(mUser.getSid());
                        if (!mUser.getSid().isEmpty()) {
                            Intent it = new Intent(LoginActivity.this, MapActivity.class);
                            startActivity(it);
                            finish();
                        }
                    }
                } else {
                    initview();
                    Toast.makeText(mContext, "用户登入失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    //登入请求码的获得
    private void loginreq() {
        mDialogView = new DialogView(this, com.wangfuhe.wfh.care.R.layout.login_request);
        mDialog = mDialogView.getView();
        final EditText request = (EditText) mDialog.findViewById(com.wangfuhe.wfh.care.R.id.et_request_num);
        mDialogView.GetDialog().setPositiveButton("请求", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mAsk = request.getText().toString();
                getuserinfo(mAsk);
                mProgressDialog = ProgressDialog.show(mContext,
                        "请稍等", "正在为您请求账号信息……", true);
            }
        }).setNegativeButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        }).show();
    }

    //修改用户信息
    private void showmodify(User user) {
        mDialogView = new DialogView(this, com.wangfuhe.wfh.care.R.layout.login_modify);
        mDialog = mDialogView.getView();
        mUserName = (EditText) mDialog.findViewById(com.wangfuhe.wfh.care.R.id.vt_user_name);
        mUserPwd = (EditText) mDialog.findViewById(com.wangfuhe.wfh.care.R.id.vt_user_pwd);
        if (user != null) {
            mUserName.setText(user.getName());
            mUserPwd.setText(user.getUPwd());
        }
        mDialogView.GetDialog().setPositiveButton("保存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                modifyuser(mUserName.getText().toString(), mUserPwd.getText().toString(), mAsk);
                mProgressDialog = ProgressDialog.show(mContext,
                        "请稍等", "正在为您保存账号信息……", true);
            }
        }).setNegativeButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        }).show();
    }

    private void modifyuser(final String name, final String pwd, final String ask) {
        Call<SCode> call = service.getResult(name, pwd, ask);
        call.enqueue(new Callback<SCode>() {
            @Override
            public void onResponse(Call<SCode> call, Response<SCode> response) {
                if (response.isSuccessful()) {
                    if (response.body().getState() == 1) {
                        mProgressDialog.dismiss();
                        loginuser(name, pwd);
                    } else {
                        Toast.makeText(mContext, "保存失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<SCode> call, Throwable t) {

            }
        });
    }
}
