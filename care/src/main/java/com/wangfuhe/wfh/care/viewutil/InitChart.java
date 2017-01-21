package com.wangfuhe.wfh.care.viewutil;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.wangfuhe.wfh.care.BassApp;
import com.wangfuhe.wfh.care.R;
import com.wangfuhe.wfh.care.model.AllData;

import java.util.ArrayList;

import static com.wangfuhe.wfh.care.BassApp.mTfLight;
import static com.wangfuhe.wfh.care.BassApp.mTfRegular;

/**
 * Created by wfh on 2016/12/9.
 */

public class InitChart {

    public static void InitLineChart(LineChart mChart, AllData data, Context context, int ChartId) {
        Description desc = new Description();
        if (ChartId == 1) {
            desc.setText("噪声数据表");
        } else {
            desc.setText("负氧离子数据表");
        }
        desc.setTextColor(Color.BLUE);
        mChart.setDescription(desc);
        mChart.getDescription().setEnabled(true);
        mChart.setTouchEnabled(true);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);

        // set an alternative background color
        mChart.setBackgroundColor(Color.LTGRAY);


        //设置标签
        MyMarkerView mv = new MyMarkerView(context, R.layout.custom_marker_view);
        mv.setChartView(mChart);
        mChart.setMarker(mv);

        //设置整个图标
        Legend l = mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
        l.setTypeface(BassApp.mTfLight);
        l.setTextColor(Color.WHITE);

        //x轴数据
        final ArrayList<String> xz = new ArrayList<>();
        for (int index = 0; index < 8; index++) {
            xz.add("xx" + index);
        }

        XAxis xl = mChart.getXAxis();
        xl.setTypeface(BassApp.mTfLight);
        xl.setTextColor(Color.WHITE);
        xl.setDrawGridLines(false);
        xl.setAvoidFirstLastClipping(true);
        xl.setEnabled(true);
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xz.get((int) value);
            }
        });


        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setAxisMaximum(120f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);
        setdatetochart(mChart, data, ChartId);

    }

    private static void setdatetochart(LineChart mChart, AllData alldata, int ChartId) {
        LineData data = new LineData();
        data.setValueTextColor(Color.WHITE);

        //create set
        final ArrayList<Entry> entries = new ArrayList<>();
        Log.i("wfh", "chart_data" + alldata.getHisSounds().isEmpty());
        //x轴数据
        final ArrayList<String> xz = new ArrayList<>();
        if (ChartId == 1) {
            for (int index = 0; index < alldata.getHisSounds().size(); index++) {
                entries.add(new Entry(index, (float) alldata.getHisSounds().get(index).getSvalue()));
                xz.add("xx" + index);
            }
        } else {
            for (int index = 0; index < alldata.getHisOxies().size(); index++) {
                entries.add(new Entry(index, (float) alldata.getHisOxies().get(index).getSvalue()));
                xz.add("xx" + index);
            }
        }
        LineDataSet set = new LineDataSet(entries, "Dynamic Data");
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(ColorTemplate.getHoloBlue());
        set.setCircleColor(Color.WHITE);
        set.setLineWidth(2f);
        set.setCircleRadius(4f);
        set.setFillAlpha(65);
        set.setFillColor(ColorTemplate.getHoloBlue());
        set.setHighLightColor(Color.rgb(244, 117, 117));
        set.setValueTextColor(Color.WHITE);
        set.setValueTextSize(9f);
        set.setDrawValues(false);

        data.addDataSet(set);
        data.notifyDataChanged();

        // let the chart know it's data has changed
        mChart.notifyDataSetChanged();

        // limit the number of visible entries
        mChart.setVisibleXRangeMaximum(120);
        // mChart.setVisibleYRange(30, AxisDependency.LEFT);

        // move to the latest entry
        mChart.moveViewToX(data.getEntryCount());

        // this automatically refreshes the chart (calls invalidate())
        // mChart.moveViewTo(data.getXValCount()-7, 55f,
        // AxisDependency.LEFT);

        mChart.setData(data);
    }

    public static void InitBarChart(BarChart mChart, AllData data, Context context) {

        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);

        mChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawGridBackground(false);
        // mChart.setDrawYLabels(false);

        //设置表名称
        Description desc = new Description();
        desc.setText("土壤数据表");
        mChart.setDescription(desc);
        //设置标签
        MyMarkerView mv = new MyMarkerView(context, R.layout.custom_marker_view);
        mv.setChartView(mChart);
        mChart.setMarker(mv);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTfLight);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(2f); // only intervals of 1 day
        xAxis.setLabelCount(7);


        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
        leftAxis.setLabelCount(8, false);
        xAxis.setGranularity(1f);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setTypeface(mTfLight);
        rightAxis.setLabelCount(8, false);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(true);
        l.setTypeface(mTfLight);
        l.setYOffset(0f);
        l.setXOffset(10f);
        l.setYEntrySpace(0f);
        l.setTextSize(11f);

        setData(data, mChart);


    }

    private static void setData(AllData allData, BarChart mChart) {


        float groupSpace = 0.08f;
        float barSpace = 0.03f; // x4 DataSet
        float barWidth = 0.2f; // x4 DataSet
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
        for (int i = 0; i < allData.getLuguhusoils().size(); i++) {
            yVals1.add(new BarEntry(i + 1, (float) allData.getLuguhusoils().get(i).getSoilHumidity()+1));
            yVals2.add(new BarEntry(i + 1, (float) allData.getLuguhusoils().get(i).getSoilTemperature()));
        }

        BarDataSet set1, set2;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(1);
            set1.setValues(yVals1);
            set2 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set2.setValues(yVals2);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "土壤湿度");
            set1.setColors(Color.rgb(104, 241, 175));
            set1.setDrawValues(false);
            set2 = new BarDataSet(yVals2, "土壤温度");
            set2.setColors(Color.rgb(164, 228, 251));
            set2.setDrawValues(false);
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set2);
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueFormatter(new LargeValueFormatter());
            data.setValueTypeface(mTfLight);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);

            mChart.setData(data);
            // specify the width each bar should have
            mChart.getBarData().setBarWidth(barWidth);

            // restrict the x-axis range
            mChart.getXAxis().setAxisMinimum(0);

            // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
            mChart.getXAxis().setAxisMaximum(0 + mChart.getBarData().getGroupWidth(groupSpace, barSpace) * 8);
            mChart.groupBars(0, groupSpace, barSpace);
            mChart.invalidate();
        }
    }

    public static void InitPieChart(PieChart mChart, AllData data) {
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

//        tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        mChart.setCenterTextTypeface(mTfLight);
        mChart.setCenterText(generateCenterSpannableText());

        mChart.setExtraOffsets(20.f, 0.f, 20.f, 0.f);

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
//        mChart.setOnChartValueSelectedListener(this);

        setData(mChart, data);

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(false);
    }

    private static SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("水质数据\n此数据来自 远程数据库");
        s.setSpan(new RelativeSizeSpan(1.5f), 0, 4, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 4, s.length() - 6, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 4, s.length() - 6, 0);
        s.setSpan(new RelativeSizeSpan(.65f), 4, s.length() - 6, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 5, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 5, s.length(), 0);
        return s;
    }

    private static void setData(PieChart mChart, AllData allData) {


        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        float temp = (float) allData.getHisWaters().get(0).getTemp();
        float cond = Float.parseFloat(allData.getHisWaters().get(0).getCond());
        float ph = Float.parseFloat(allData.getHisWaters().get(0).getPh());
        float oxy = Float.parseFloat(allData.getHisWaters().get(0).getOxy());
        float turb = Float.parseFloat(allData.getHisWaters().get(0).getTurb());
        float sum = temp + cond + ph + oxy + turb;


        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        entries.add(new PieEntry(turb, "温度"));
        entries.add(new PieEntry(cond, "电导率"));
        entries.add(new PieEntry(ph, "PH值"));
        entries.add(new PieEntry(oxy, "溶解氧"));
        entries.add(new PieEntry(turb, "浊度"));

        PieDataSet dataSet = new PieDataSet(entries, "Election Results");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(6f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);


        dataSet.setValueLinePart1OffsetPercentage(sum);
        dataSet.setValueLinePart1Length(0.2f);
        dataSet.setValueLinePart2Length(0.4f);
        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);
        data.setValueTypeface(mTfRegular);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }

    public static void InitRadarChart(RadarChart mChart, AllData data, Context context) {
        mChart.setBackgroundColor(Color.rgb(60, 65, 82));

        mChart.getDescription().setEnabled(false);

        mChart.setWebLineWidth(1f);
        mChart.setWebColor(Color.LTGRAY);
        mChart.setWebLineWidthInner(1f);
        mChart.setWebColorInner(Color.LTGRAY);
        mChart.setWebAlpha(100);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MarkerView mv = new RadarMarkerView(context, R.layout.radar_markerview);
        mv.setChartView(mChart); // For bounds control
        mChart.setMarker(mv); // Set the marker to the chart

        setData(mChart, data);

        mChart.animateXY(
                1400, 1400,
                Easing.EasingOption.EaseInOutQuad,
                Easing.EasingOption.EaseInOutQuad);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setTypeface(mTfLight);
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            private String[] mActivities = new String[]{"温度", "湿度", "露点温度", "气压", "风向","风速","最大风速"
            ,"辐照度","雨量","电量"};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mActivities[(int) value % mActivities.length];
            }
        });
        xAxis.setTextColor(Color.WHITE);

        YAxis yAxis = mChart.getYAxis();
        yAxis.setTypeface(mTfLight);
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
//        yAxis.setAxisMaximum(80f);
        yAxis.setDrawLabels(false);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setTypeface(mTfLight);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        l.setTextColor(Color.WHITE);
    }

    private static void setData(RadarChart mChart, AllData allData) {

        float mult = 80;
        float min = 20;
        int cnt = 10;

        ArrayList<RadarEntry> entries1 = new ArrayList<RadarEntry>();
        ArrayList<RadarEntry> entries2 = new ArrayList<RadarEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        //获取数据
        float t_air = (float) allData.getLuguhuwinds().get(0).getT_air()*100;
        float hum = (float) allData.getLuguhuwinds().get(0).getHum()*10;
        float dew = (float) allData.getLuguhuwinds().get(0).getDew()*10;
        float baro = (float) allData.getLuguhuwinds().get(0).getBaro();
        float wdir = (float) allData.getLuguhuwinds().get(0).getWdir();
        float wspd = (float) allData.getLuguhuwinds().get(0).getWspd()*1000;
        float ws_max = (float) allData.getLuguhuwinds().get(0).getWs_max()*1000;
        float srad = (float) allData.getLuguhuwinds().get(0).getSrad();
        float rainf = (float) allData.getLuguhuwinds().get(0).getRainf()*10+1000;
        float batt = (float) allData.getLuguhuwinds().get(0).getBatt()*10;
        //添加数据
        entries1.add(new RadarEntry(t_air));
        entries1.add(new RadarEntry(hum));
        entries1.add(new RadarEntry(dew));
        entries1.add(new RadarEntry(baro));
        entries1.add(new RadarEntry(wdir));
        entries1.add(new RadarEntry(wspd));
        entries1.add(new RadarEntry(ws_max));
        entries1.add(new RadarEntry(srad));
        entries1.add(new RadarEntry(rainf));
        entries1.add(new RadarEntry(batt));


        for (int i = 0; i < cnt; i++) {
            float val2 = (float) (Math.random() * mult) + min;
            entries2.add(new RadarEntry(val2));
        }

        RadarDataSet set1 = new RadarDataSet(entries1, "真实数据");
        set1.setColor(Color.rgb(103, 110, 129));
        set1.setFillColor(Color.rgb(103, 110, 129));
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);

        RadarDataSet set2 = new RadarDataSet(entries2, "This Week");
        set2.setColor(Color.rgb(121, 162, 175));
        set2.setFillColor(Color.rgb(121, 162, 175));
        set2.setDrawFilled(true);
        set2.setFillAlpha(180);
        set2.setLineWidth(2f);
        set2.setDrawHighlightCircleEnabled(true);
        set2.setDrawHighlightIndicators(false);

        ArrayList<IRadarDataSet> sets = new ArrayList<IRadarDataSet>();
        sets.add(set1);
        sets.add(set2);

        RadarData data = new RadarData(sets);
        data.setValueTypeface(mTfLight);
        data.setValueTextSize(8f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.WHITE);

        mChart.setData(data);
        mChart.invalidate();
    }
}
