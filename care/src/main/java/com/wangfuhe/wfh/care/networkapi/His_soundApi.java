package com.wangfuhe.wfh.care.networkapi;

import com.wangfuhe.wfh.care.model.His_sound;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by wfh on 2016/11/29.
 */

public interface His_soundApi {
    @FormUrlEncoded
    @POST("find")
    Call<List<His_sound>> getSound(@Field("sid") String sid);
    @FormUrlEncoded
    @POST("find")
    Observable<List<His_sound>> getSoundByRx(@Field("sid") String sid);
    @FormUrlEncoded
    @POST("find")
    Observable<List<His_sound>> getSoundByRx(@Field("sid") String sid,@Field("btime") String btime
            ,@Field("etime") String etime);
}
