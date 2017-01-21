package com.wangfuhe.wfh.care.networkapi;

import com.wangfuhe.wfh.care.model.Luguhusoil;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by wfh on 2016/12/6.
 */

public interface LuguhusoilApi {
    @FormUrlEncoded
    @POST("find")
    Observable<List<Luguhusoil>> getLuguhusoilByRx(@Field("sid") String sid);
    @FormUrlEncoded
    @POST("find")
    Observable<List<Luguhusoil>> getLuguhusoilByRx(@Field("sid") String sid, @Field("btime") String btime
            , @Field("etime") String etime);
}
