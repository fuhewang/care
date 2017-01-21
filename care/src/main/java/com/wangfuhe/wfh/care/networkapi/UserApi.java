package com.wangfuhe.wfh.care.networkapi;

import com.wangfuhe.wfh.care.ResultBean.SCode;
import com.wangfuhe.wfh.care.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by wfh on 2016/11/27.
 */

public interface UserApi {
    @FormUrlEncoded
    @POST("request")
    Call<User> PostRequest(@Field("ask") String ask);
    @FormUrlEncoded
    @POST("login/update")
    Call<SCode> getResult(@Field("name") String name, @Field("pwd") String pwd, @Field("ask") String ask);
    @FormUrlEncoded
    @POST("login")
    Call<User> loginResult(@Field("name") String name, @Field("pwd") String pwd);
}
