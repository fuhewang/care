package com.wangfuhe.wfh.care.ViewActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.shizhefei.view.viewpager.SViewPager;
import com.wangfuhe.wfh.care.R;
import com.wangfuhe.wfh.care.ViewFragment.FirstLayerFragment;
import com.wangfuhe.wfh.care.model.AllData;
import com.wangfuhe.wfh.care.networkutil.GetDataListener;
import com.wangfuhe.wfh.care.networkutil.GetDataService;

public class IndexActivity extends FragmentActivity {
    private IndicatorViewPager mViewPager;
    private View mCenterView;
    private FixedIndicatorView mIndicator;
    private AllData mAllDate;
    private GetDataService mService;
    private GetDataListener mListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ListenerData();
        initView();
        setListener();
    }

    private void ListenerData() {
        Intent it=new Intent(IndexActivity.this,GetDataService.class);
        bindService(it,mConnection, Context.BIND_AUTO_CREATE);
    }
    public void DataChange(GetDataListener listener){
        this.mListener=listener;
    }
    private ServiceConnection mConnection =new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                     mService=((GetDataService.MsgBinder) iBinder).getService();
                     mService.AddAllDate(new GetDataListener() {
                         @Override
                         public void addDate(AllData date) {
                             mAllDate=date;
                             if (mListener!=null)
                                 mListener.addDate(mAllDate);
                         }
                     });
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    private void setListener() {
        mCenterView.setOnClickListener(mOnClickListener);
    }

    private void initView() {
        getWindow().setStatusBarColor(Color.rgb(8,234,242));
        SViewPager viewPager= (SViewPager) findViewById(R.id.index_viewpager);
        mIndicator= (FixedIndicatorView) findViewById(R.id.index_indicator);
        mIndicator.setOnTransitionListener(new OnTransitionTextListener().setColor(Color.RED,Color.GRAY));
        //添加一个view 作为CenterView,位于Indicator的tab中间
        mCenterView=getLayoutInflater().inflate(R.layout.tab_main_center,mIndicator,false);
        mIndicator.setCenterView(mCenterView);
        mViewPager=new IndicatorViewPager(mIndicator,viewPager);
//        mViewPager.setAdapter();
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager(),mAllDate));
        // 禁止viewpager的滑动事件
        viewPager.setCanScroll(false);
        // 设置viewpager保留界面不重新加载的页面数量
        viewPager.setOffscreenPageLimit(4);
    }
   private View.OnClickListener mOnClickListener= new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           if(view ==mCenterView){
               //准备添加设备
           }
       }
   };

    private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter{
        private String[] tabNames={"数据","个人"};
        private int[] tabIcons={R.drawable.maintab_chart_selector,R.drawable.maintab_per_selector};
        private LayoutInflater mInflater;
        private AllData mAllData;
        public MyAdapter(FragmentManager fragmentManager,AllData data) {
            super(fragmentManager);
            mInflater=LayoutInflater.from(getApplicationContext());
            this.mAllData=data;
        }

        @Override
        public int getCount() {
            return tabNames.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView ==null){
                convertView=mInflater.inflate(R.layout.tab_main,container,false);
            }
            TextView textView= (TextView) convertView;
            textView.setText(tabNames[position]);
            textView.setCompoundDrawablesWithIntrinsicBounds(0,tabIcons[position],0,0);
            return textView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            FirstLayerFragment mainFragment =new FirstLayerFragment();
            Bundle bundle=new Bundle();
            bundle.putString(FirstLayerFragment.INTENT_STRING_TABNAME,tabNames[position]);
            bundle.putInt(FirstLayerFragment.INTENT_INT_INDEX,position);
            bundle.putSerializable(FirstLayerFragment.INTENT_DATA_DATA,mAllData);
            mainFragment.setArguments(bundle);
            return mainFragment;
        }
    }
}
