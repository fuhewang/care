package com.wangfuhe.wfh.care.networkutil;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.wangfuhe.wfh.care.model.AllData;
import com.wangfuhe.wfh.care.networkapi.AllDateApi;
import com.wangfuhe.wfh.care.networkapi.His_OxyApi;
import com.wangfuhe.wfh.care.networkapi.His_soundApi;
import com.wangfuhe.wfh.care.networkapi.His_waterApi;
import com.wangfuhe.wfh.care.networkapi.LuguhusoilApi;
import com.wangfuhe.wfh.care.networkapi.LuguhuwindApi;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GetDataService extends Service {
    private String sid;
    private AllData mAllData;
    private AllDateApi mDateApi;
    private His_OxyApi mOxyApi;
    private His_soundApi mSoundApi;
    private His_waterApi mWaterApi;
    private LuguhusoilApi mSoilApi;
    private LuguhuwindApi mWindApi;
    private GetDataListener mListener;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1) {
                Log.i("wfh","移除消息");
                mHandler.removeMessages(0);
            }else {
                mHandler.sendEmptyMessageDelayed(0,60*1000);
                if (sid!=null)
                    GetDate(sid);
            }
        }
    };
    public GetDataService() {
        super();
    }

    public void AddAllDate(GetDataListener listener){
        this.mListener=listener;
    }

    /**
     * 返回一个ibinder
     *
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        return new MsgBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sid = intent.getStringExtra("sid");
        if(sid!=null){
            initService();
            GetDate(sid);
            mHandler.sendEmptyMessageDelayed(0,30000);
        }
        mHandler.sendEmptyMessageDelayed(1,2*60000);
        return START_STICKY;

    }

    private void GetDate(String sid) {
        Log.i("wfh","发动请求了");
        mDateApi.getalldateByRx(sid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AllData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("wfh", "请求失败"+e.toString());
                    }

                    @Override
                    public void onNext(AllData date) {
                        if (date != null) {
                            Log.i("wfh", "alldate请求成功"+date.getLuguhusoils().isEmpty());
                            if (mListener!=null)
                            mListener.addDate(date);
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public class MsgBinder extends Binder {
        /**
         * 获取当前Service的实例
         *
         * @return
         */
        public GetDataService getService() {
            return GetDataService.this;
        }
    }

    public void initService() {
        mSoundApi = NetWorkTool.GetHis_SoundService();
        mOxyApi = NetWorkTool.GetOxyService();
        mWaterApi = NetWorkTool.GetWaterService();
        mSoilApi = NetWorkTool.GetSoilService();
        mWindApi = NetWorkTool.GetWindService();
        mDateApi=NetWorkTool.GetAllDateService();
    }
}
