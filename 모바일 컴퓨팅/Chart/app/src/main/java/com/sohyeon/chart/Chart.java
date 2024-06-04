package com.sohyeon.chart;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class Chart extends Activity {

    private LineChart lineChart;


    @Override
        public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart);

        int Edit1 = new Integer(10);
        int Edit2 = new Integer(10);
        int Edit3 = new Integer(10);
        int Edit4 = new Integer(10);
        int Edit5 = new Integer(10);

        EditText a = (EditText)findViewById(R.id.edit1);
        //Edit1 = a.getInputType();
        EditText b = (EditText) findViewById(R.id.edit2);
        //Edit2 = b.getInputType();
        EditText c = (EditText) findViewById(R.id.edit3);
        //Edit3 = c.getInputType();
        EditText d = (EditText) findViewById(R.id.edit4);
        //Edit4 = d.getInputType();
        EditText e = (EditText) findViewById(R.id.edit5);
        //Edit5 = e.getInputType();

        Edit1 = Integer.parseInt(String.valueOf(a.getText())); //정수값 가져오기
        Edit2 = Integer.parseInt(String.valueOf(b.getText()));
        Edit3 = Integer.parseInt(String.valueOf(c.getText()));
        Edit4 = Integer.parseInt(String.valueOf(d.getText()));
        Edit5 = Integer.parseInt(String.valueOf(e.getText()));

        lineChart = (LineChart) findViewById(R.id.chart);

        List<Entry> yValues = new ArrayList<>(); //값 받아오기
        //ArrayList<Entry> yValues = new ArrayList<Entry>();
        yValues.add(new Entry(1, Edit1));
        yValues.add(new Entry(2, Edit2));
        yValues.add(new Entry(3, Edit3));
        yValues.add(new Entry(4, Edit4));
        yValues.add(new Entry(5, Edit5));

        LineDataSet lineDataSet = new LineDataSet(yValues, "시간");
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setCircleColorHole(Color.BLUE);
        lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawValues(false);

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);
        xAxis.enableGridDashedLine(8, 24, 0);

        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);

        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        Description description = new Description();
        description.setText("");

        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setDescription(description);
        lineChart.animateY(2000, Easing.EasingOption.EaseInCubic);
        lineChart.invalidate();
    }
}