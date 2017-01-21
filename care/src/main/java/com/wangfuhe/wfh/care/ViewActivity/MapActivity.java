package com.wangfuhe.wfh.care.ViewActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.wangfuhe.wfh.care.BaseActivity;
import com.wangfuhe.wfh.care.R;
import com.wangfuhe.wfh.care.model.Base_sound;
import com.wangfuhe.wfh.care.networkapi.Base_soundApi;
import com.wangfuhe.wfh.care.networkutil.GetDataService;
import com.wangfuhe.wfh.care.networkutil.NetWorkTool;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapActivity extends BaseActivity {

    private MapView mMapView = null;
    private BaiduMap mBaiduMap = null;
    private List<Base_sound> mSounds;
    private Base_soundApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);
        initview();
        getBaseSound();
    }

    private void getBaseSound() {
        service = NetWorkTool.GetBaseService();
        mUser = query();
        if (mUser != null) mUser.setSid(getsid("sid"));
        if (!mUser.getSid().isEmpty()) {
            for (String s : mUser.getSid()) {
                Call<Base_sound> call = service.getBase(s);
                call.enqueue(new Callback<Base_sound>() {
                    @Override
                    public void onResponse(Call<Base_sound> call, Response<Base_sound> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            mSounds.add(response.body());
                            if (mSounds.size() == mUser.getSid().size()) {
                                setmap();
                            }
                        } else {
                            setToask("请求失败");
                        }
                    }

                    @Override
                    public void onFailure(Call<Base_sound> call, Throwable t) {

                    }
                });
            }
        } else {
            setToask("该用户没有添加设备！");
        }
    }

    private void setmap() {
        ArrayList<LatLng> mLats = new ArrayList<>();
        ArrayList<OverlayOptions> moptions = new ArrayList<>();

        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.marker);
        for (Base_sound sound : mSounds) {
            LatLng lat = new LatLng(sound.getLat(), sound.getLon());
            OverlayOptions options = new MarkerOptions().position(lat)
                    .icon(bitmap)
                    .title(sound.getLoc())
                    .zIndex(9)  //设置marker所在层级
                    .draggable(true);  //设置手势拖拽;
            mLats.add(lat);
            moptions.add(options);
        }
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(mLats.get(0));
        mBaiduMap.addOverlays(moptions);
        mBaiduMap.animateMapStatus(msu);
        setMapListener();
    }

    private void setMapListener() {
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(MapActivity.this, marker.getTitle() + marker.getPosition().latitude + "", Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });

        mBaiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                String mSoundName = marker.getTitle();
                String id = null;
                for (Base_sound s : mSounds) {
                    if (s.getLoc().equals(mSoundName)) {
                        id = s.getSid();
                        break;
                    }

                }

                Intent it = new Intent(MapActivity.this, GetDataService.class);
                Intent it2=new Intent(MapActivity.this,IndexActivity.class);
                it.putExtra("sid", id);
                startService(it);
                startActivity(it2);
                marker.remove();
            }

            @Override
            public void onMarkerDragStart(Marker marker) {

            }
        });
    }

    private void initview() {
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        mSounds = new ArrayList<>();
    }
}
