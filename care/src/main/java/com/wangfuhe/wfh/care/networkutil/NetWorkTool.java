package com.wangfuhe.wfh.care.networkutil;

import com.wangfuhe.wfh.care.networkapi.AllDateApi;
import com.wangfuhe.wfh.care.networkapi.Base_soundApi;
import com.wangfuhe.wfh.care.networkapi.His_OxyApi;
import com.wangfuhe.wfh.care.networkapi.His_soundApi;
import com.wangfuhe.wfh.care.networkapi.His_waterApi;
import com.wangfuhe.wfh.care.networkapi.LuguhusoilApi;
import com.wangfuhe.wfh.care.networkapi.LuguhuwindApi;
import com.wangfuhe.wfh.care.networkapi.UserApi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wfh on 2016/11/28.
 */

public class NetWorkTool {
    private static Retrofit mRetrofit;
    private static UserApi service;
    private static final String USER_URL = "http://10.0.2.2:8080/MyService/jaxrs/user/";
    private static final String BASE_URL = "http://10.0.2.2:8080/MyService/jaxrs/base_sound/";
    private static final String HIS_SOUND_URL = "http://10.0.2.2:8080/MyService/jaxrs/his_sound/";
    private static final String HIS_OXY_URL = "http://10.0.2.2:8080/MyService/jaxrs/his_oxy/";
    private static final String HIS_WATER_URL = "http://10.0.2.2:8080/MyService/jaxrs/his_water/";
    private static final String LUGUHUSOIL_URL = "http://10.0.2.2:8080/MyService/jaxrs/luguhusoil/";
    private static final String LUGUHUWIND_URL = "http://10.0.2.2:8080/MyService/jaxrs/luguhuwind/";
    private static final String ALLDATE_URL = "http://10.0.2.2:8080/MyService/jaxrs/alldate/";

    //进行网络请求获取用户信息
    public static UserApi GetUserService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(USER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = mRetrofit.create(UserApi.class);
        return service;
    }

    //获取设备信息
    public static Base_soundApi GetBaseService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return mRetrofit.create(Base_soundApi.class);
    }

    //获取噪声信息
    public static His_soundApi GetHis_SoundService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(HIS_SOUND_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return mRetrofit.create(His_soundApi.class);
    }

    //获取水质信息
    public static His_waterApi GetWaterService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(HIS_WATER_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return mRetrofit.create(His_waterApi.class);
    }

    //获取负氧离子信息
    public static His_OxyApi GetOxyService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(HIS_OXY_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return mRetrofit.create(His_OxyApi.class);
    }

    //获取泸沽湖土壤信息
    public static LuguhusoilApi GetSoilService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(LUGUHUSOIL_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return mRetrofit.create(LuguhusoilApi.class);
    }

    //获取泸沽湖天气信息
    public static LuguhuwindApi GetWindService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(LUGUHUWIND_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return mRetrofit.create(LuguhuwindApi.class);
    }
    //获取所有数据
    public static AllDateApi GetAllDateService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(ALLDATE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return mRetrofit.create(AllDateApi.class);
    }
}
