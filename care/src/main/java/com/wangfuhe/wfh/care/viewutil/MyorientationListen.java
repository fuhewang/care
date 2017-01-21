package com.wangfuhe.wfh.care.viewutil;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by wfh on 2016/4/3.
 */
public class MyorientationListen implements SensorEventListener {

    private SensorManager msensorManager;
    private Context mcontext;
    private Sensor mSensor;
    private float lastx;
    public MyorientationListen(Context mcontext) {
        this.mcontext = mcontext;
    }

    public void start(){
        msensorManager= (SensorManager) mcontext.getSystemService(Context.SENSOR_SERVICE);
        if(msensorManager!=null){
            mSensor=msensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        }
        if(mSensor!=null){
            msensorManager.registerListener(this,
                    mSensor,SensorManager.SENSOR_DELAY_UI);
        }
    }

    public void stop(){
        msensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_ORIENTATION){
            float x=event.values[SensorManager.DATA_X];

            if(Math.abs(x-lastx)>1.0){
                if(mOnOrientationListen!=null){
                    mOnOrientationListen.onOrientationChanged(x);
                }
            }
            lastx=x;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    private OnOrientationListen mOnOrientationListen;

    public void setOnOrientationListen(OnOrientationListen mOnOrientationListen) {
        this.mOnOrientationListen = mOnOrientationListen;
    }
    public  interface  OnOrientationListen{
        void onOrientationChanged(float x);
    }


}
