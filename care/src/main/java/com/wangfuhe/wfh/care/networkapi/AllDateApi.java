package com.wangfuhe.wfh.care.networkapi;

import com.wangfuhe.wfh.care.model.AllData;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by wfh on 2016/12/6.
 */

public interface AllDateApi {
    @FormUrlEncoded
    @POST("find")
    Observable<AllData> getalldateByRx(@Field("sid") String sid);
    @FormUrlEncoded
    @POST("find")
    Observable<AllData> getalldateByRx(@Field("sid") String sid, @Field("btime") String btime
            , @Field("etime") String etime);
}
