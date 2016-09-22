package com.tcs.myfund.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.tcs.myfund.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;

import static com.tcs.myfund.fragments.LineGraphFragment.GraphPeriod.FIVE_YEARS;
import static com.tcs.myfund.fragments.LineGraphFragment.GraphPeriod.ONE_MONTH;
import static com.tcs.myfund.fragments.LineGraphFragment.GraphPeriod.ONE_YEAR;
import static com.tcs.myfund.fragments.LineGraphFragment.GraphPeriod.THREE_YEARS;


/**
 * Created by 778363aypp on 9/21/2016.
 */

public class LineGraphFragment extends BaseFragment {
    RadioGroup radioGroup;
    LineChart lineChart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.line_graph_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        radioGroup = (RadioGroup) view.findViewById(R.id.graph_view_radio_group);
        lineChart = (LineChart) view.findViewById(R.id.line_chart);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_1_month:
                        drawGraph(ONE_MONTH);
                        break;
                    case R.id.radio_1_Year:
                        drawGraph(ONE_YEAR);
                        break;
                    case R.id.radio_3_year:
                        drawGraph(THREE_YEARS);
                        break;
                    case R.id.radio_5_year:
                        drawGraph(FIVE_YEARS);
                        break;
                }
            }
        });
        radioGroup.check(R.id.radio_1_Year);
    }


    private ArrayList<Entry> getEntryFromSet(GraphPeriod graphPeriod) {
        List<String> valuesList = new ArrayList<>();
        switch (graphPeriod) {
            case ONE_MONTH:
                valuesList.add("20");
                valuesList.add("10");
                valuesList.add("30");
                valuesList.add("50");
                valuesList.add("25");
                break;
            case ONE_YEAR:
                valuesList.add("10");
                valuesList.add("30");
                valuesList.add("50");
                valuesList.add("20");
                valuesList.add("45");
                valuesList.add("10");
                valuesList.add("30");
                valuesList.add("50");
                valuesList.add("20");
                valuesList.add("45");
                valuesList.add("20");
                valuesList.add("45");

                break;
            case THREE_YEARS:
                valuesList.add("30");
                valuesList.add("40");
                valuesList.add("60");
                valuesList.add("80");
                valuesList.add("34");
                break;
            case FIVE_YEARS:
                valuesList.add("10");
                valuesList.add("60");
                valuesList.add("30");
                valuesList.add("80");
                valuesList.add("45");
                break;
        }
        ArrayList<String> collectionSet = new ArrayList<>(valuesList);
        ArrayList<Entry> entryList = new ArrayList<>();

        for (int i = 0; i < collectionSet.size(); i++) {
            entryList.add(new Entry(Float.parseFloat(String.valueOf(collectionSet.get(i))), i));
        }
        return entryList;
    }

    public List<String> getLineData(GraphPeriod graphPeriod) {
        List<String> lineData = new ArrayList<>();
        switch (graphPeriod) {
            case ONE_YEAR:
                lineData.add("J");
                lineData.add("F");
                lineData.add("M");
                lineData.add("A");
                lineData.add("M");
                lineData.add("J");
                lineData.add("J");
                lineData.add("A");
                lineData.add("S");
                lineData.add("O");
                lineData.add("N");
                lineData.add("D");

                break;

            case ONE_MONTH:
            case THREE_YEARS:
            case FIVE_YEARS:
                lineData.add("5");
                lineData.add("10");
                lineData.add("15");
                lineData.add("20");
                lineData.add("25");
                break;
        }
        ;
        return lineData;
    }

    private void drawGraph(GraphPeriod graphPeriod) {
        LineDataSet dataset = null;
        LineData data = null;
        switch (graphPeriod) {
            case ONE_MONTH:
                dataset = new LineDataSet(getEntryFromSet(ONE_MONTH), "Yearly Rate");
                data = new LineData(getLineData(ONE_MONTH), dataset);
                break;
            case ONE_YEAR:
                dataset = new LineDataSet(getEntryFromSet(ONE_YEAR), "Yearly Rate");
                data = new LineData(getLineData(ONE_YEAR), dataset);
                break;
            case THREE_YEARS:
                dataset = new LineDataSet(getEntryFromSet(THREE_YEARS), "Yearly Rate");
                data = new LineData(getLineData(THREE_YEARS), dataset);
                break;
            case FIVE_YEARS:
                dataset = new LineDataSet(getEntryFromSet(FIVE_YEARS), null);
                data = new LineData(getLineData(FIVE_YEARS), dataset);
                break;
        }
        dataset.setDrawFilled(true);
        int colorPrimary = getResources().getColor(R.color.colorPrimary);
        dataset.setColor(colorPrimary);
        dataset.setFillColor(colorPrimary);
        dataset.setFillAlpha(80);
        dataset.setDrawValues(false);
        dataset.setDrawCircles(false);
        lineChart.setData(data);
        lineChart.setTouchEnabled(false);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        lineChart.getXAxis().setDrawGridLines(false);

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);

        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getAxis(YAxis.AxisDependency.RIGHT).setDrawGridLines(false);
        lineChart.getXAxis().setDrawAxisLine(false);
        lineChart.getAxisLeft().setDrawAxisLine(false);
        lineChart.getAxis(YAxis.AxisDependency.RIGHT).setDrawAxisLine(false);

        lineChart.setDescription("");

        lineChart.animateY(500);
    }


    public enum GraphPeriod {
        ONE_MONTH,
        ONE_YEAR,
        THREE_YEARS,
        FIVE_YEARS
    }
}
