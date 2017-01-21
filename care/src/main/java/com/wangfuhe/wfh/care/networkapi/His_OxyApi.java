package com.wangfuhe.wfh.care.networkapi;

import com.wangfuhe.wfh.care.model.His_oxy;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by wfh on 2016/12/6.
 */

public interface His_OxyApi {
    @FormUrlEncoded
    @POST("find")
    Observable<List<His_oxy>> getoxyByRx(@Field("sid") String sid);
    @FormUrlEncoded
    @POST("find")
    Observable<List<His_oxy>> getoxyByRx(@Field("sid") String sid,@Field("btime") String btime
            ,@Field("etime") String etime);
}
