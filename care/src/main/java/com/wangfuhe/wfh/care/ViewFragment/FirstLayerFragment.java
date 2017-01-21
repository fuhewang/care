package com.wangfuhe.wfh.care.ViewFragment;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shizhefei.fragment.LazyFragment;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.DrawableBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.wangfuhe.wfh.care.R;
import com.wangfuhe.wfh.care.ViewActivity.IndexActivity;
import com.wangfuhe.wfh.care.model.AllData;
import com.wangfuhe.wfh.care.networkutil.GetDataListener;


/**
 * Created by wfh on 2016/12/7.
 */

public class FirstLayerFragment extends LazyFragment {
    private IndicatorViewPager mViewPager;
    private LayoutInflater mInflater;
    private AllData mData;
    private ScrollIndicatorView mScrollIndicatorView;
    public static final String INTENT_STRING_TABNAME="intent_String_tabname";
    public static final String INTENT_INT_INDEX="intent_int_index";
    public static final String INTENT_DATA_DATA="intent_data_data";
    public static int sInt;
    private String mTabName;
    private int mIndex;
    private String[] name={"全体数据","噪声数据","负氧离子数据","土壤数据","水质数据","气象数据"};
    private int unSelectTextColor;
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_tabmain);
        Resources res=getResources();

        Bundle bundle=getArguments();
        mTabName=bundle.getString(INTENT_STRING_TABNAME);
        mIndex=bundle.getInt(INTENT_INT_INDEX);
        mData= (AllData) bundle.getSerializable(INTENT_DATA_DATA);
        ViewPager viewPager= (ViewPager) findViewById(R.id.fragment_tabmain_viewPager);
        mScrollIndicatorView= (ScrollIndicatorView) findViewById(R.id.fragment_tabmain_indicator);
        mScrollIndicatorView.setBackgroundColor(Color.rgb(8,234,242));
        mScrollIndicatorView.setScrollBar(new DrawableBar(getContext(),R.drawable.round_border_white_selector, ScrollBar.Gravity.CENTENT_BACKGROUND) {
            @Override
            public int getHeight(int tabHeight) {
                return tabHeight - dipToPix(12);
            }

            @Override
            public int getWidth(int tabWidth) {
                return tabWidth - dipToPix(12);
            }
        });
        unSelectTextColor = Color.WHITE;
        mScrollIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(Color.BLUE, unSelectTextColor));
        viewPager.setOffscreenPageLimit(5);
        mViewPager=new IndicatorViewPager(mScrollIndicatorView,viewPager);
        mScrollIndicatorView.setPinnedTabView(true);
        mInflater=LayoutInflater.from(getApplicationContext());
        // 设置固定tab的shadow，这里不设置的话会使用默认的shadow绘制
        mScrollIndicatorView.setPinnedShadow(R.drawable.tabshadow, dipToPix(4));
        mScrollIndicatorView.setPinnedTabBgColor(Color.rgb(8,234,242));
        mViewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        ((IndexActivity)getActivity()).DataChange(new GetDataListener() {
            @Override
            public void addDate(AllData date) {
                Log.i("wfh","first数据改变了");
                mData=date;
                mViewPager.notifyDataSetChanged();
            }
        });
    }

    private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter{
        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return name.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView==null){
                convertView =mInflater.inflate(R.layout.tab_top,container,false);
            }
            TextView textView= (TextView) convertView;
            textView.setText(name[position]);
            int padding=dipToPix(10);
            textView.setPadding(padding,0,padding,0);
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            MoreFragment fragment = new MoreFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(MoreFragment.INTENT_INT_INDEX, position);
            bundle.putSerializable(MoreFragment.INTENT_DATA_DATA,mData);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }
    }
    /**
     * 根据dip值转化成px值
     *
     * @param dip
     * @return
     */
    private int dipToPix(float dip) {
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip,
                getResources().getDisplayMetrics());
        return size;
    }
}