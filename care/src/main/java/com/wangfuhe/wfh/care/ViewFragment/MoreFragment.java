package com.wangfuhe.wfh.care.ViewFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.shizhefei.fragment.LazyFragment;
import com.wangfuhe.wfh.care.R;
import com.wangfuhe.wfh.care.model.AllData;
import com.wangfuhe.wfh.care.viewutil.InitChart;

/**
 * Created by wfh on 2016/12/8.
 */

public class MoreFragment extends LazyFragment {
    private ProgressBar progressBar;
    private int tabIndex;
    public static final String INTENT_INT_INDEX = "intent_int_index";
    public static final String INTENT_DATA_DATA = "intent_data_data";
    private AllData mData;
    private LineChart mChart;
    private BarChart mBarChart;
    private PieChart mPieChart;
    private RadarChart mRadarChart;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);

        setContentView(R.layout.fragment_tabmain_item);
        tabIndex = getArguments().getInt(INTENT_INT_INDEX);
        mData = (AllData) getArguments().getSerializable(INTENT_DATA_DATA);
        progressBar = (ProgressBar) findViewById(R.id.fragment_mainTab_item_progressBar);
        initchart();
        if (mData != null) {
            progressBar.setVisibility(View.GONE);
            if (tabIndex % 6 == 3) {
                mBarChart.setVisibility(View.VISIBLE);
                InitChart.InitBarChart(mBarChart,mData,getActivity());
            } else if (tabIndex % 6 == 4) {
                mPieChart.setVisibility(View.VISIBLE);
                InitChart.InitPieChart(mPieChart,mData);
            } else if (tabIndex % 6 == 5) {
                mRadarChart.setVisibility(View.VISIBLE);
                InitChart.InitRadarChart(mRadarChart,mData,getActivity());
            } else{
                mChart.setVisibility(View.VISIBLE);
                InitChart.InitLineChart(mChart, mData,getActivity(),tabIndex);
            }
        }
    }

    private void initchart() {
        mChart = (LineChart) findViewById(R.id.chart1);
        mChart.setVisibility(View.GONE);
        mBarChart = (BarChart) findViewById(R.id.chart2);
        mBarChart.setVisibility(View.GONE);

        mPieChart = (PieChart) findViewById(R.id.chart4);
        mPieChart.setVisibility(View.GONE);

        mRadarChart = (RadarChart) findViewById(R.id.chart3);
        mRadarChart.setVisibility(View.GONE);

    }


    @Override
    public void onDestroyViewLazy() {
        super.onDestroyViewLazy();
    }

}
