package com.wangfuhe.wfh.care.networkapi;

import com.wangfuhe.wfh.care.model.Base_sound;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by wfh on 2016/11/28.
 */

public interface Base_soundApi {
    @FormUrlEncoded
    @POST("find/sid")
    Call<Base_sound> getBase(@Field("sid") String sid);
}
