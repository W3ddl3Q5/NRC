package com.example.imp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointF;
import com.plattysoft.leonids.ParticleSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    HorizontalBarChart mChart;
    ArrayList<Float> data = new ArrayList<>();
    Boolean detect_button;
    ImageButton button;
    TextView distance;
    ParticleSystem ps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        distance = findViewById(R.id.track_distance);
        button = findViewById(R.id.control);
        detect_button = false;
        data.add((float)8);
        data.add((float)5);
        data.add((float)4);

        mChart = findViewById(R.id.chart1);

        setData(3, 3);

        // Launch 2 particle systems one for each image
        ps = new ParticleSystem(this ,100, R.drawable.star_white_border, 800);
        ps.setScaleRange(0.9f, 1.3f);
        ps.setSpeedRange(0.1f, 0.25f);
        ps.setAcceleration(0.00001f, 90);
        ps.setRotationSpeedRange(90, 180);
        ps.setFadeOut(400, new AccelerateInterpolator());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (detect_button){
                    button.setImageResource((R.drawable.pause));
                    detect_button = false;
                    distance.setText("0.00");
                }
                else{
                    button.setImageResource(R.drawable.play);
                    detect_button = true;
                    distance.setText("4.39");
                    achievements();
                    ps.oneShot(v, 70);

                }

            }
        });
    }



    private void setData(int count, int range){
        ArrayList<BarEntry> yVals = new ArrayList<>();
        float barwidth = 0.1f;
        float spaceForBar = 0.2f;

        for (int i = 0; i < count; i ++){
            float val = (data.get(i)*range);
            yVals.add(new BarEntry(i*spaceForBar, val));
        }

        BarDataSet set1;

        set1 = new BarDataSet(yVals,"8KM, 5KM, 4KM");
        set1.setColors(new int[] { R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent}, getApplicationContext());

        BarData data = new BarData(set1);

        data.setValueTextSize(1f);
        data.setBarWidth(barwidth);
        data.setDrawValues(false);
        mChart.setData(data);

        mChart.getXAxis().setDrawGridLines(false);
        mChart.getAxisLeft().setDrawGridLines(false);
        mChart.getAxisRight().setDrawGridLines(false);
        mChart.setDrawBarShadow(false);
        mChart.setDrawGridBackground(false);
        mChart.getLegend().setEnabled(false);
        mChart.getXAxis().setDrawLabels(false);

        // style chart
        mChart.setDrawBorders(false);
        mChart.setAutoScaleMinMaxEnabled(true);

        // remove axis
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setEnabled(false);
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setEnabled(false);

        // hide legend
        Legend legend = mChart.getLegend();
        legend.setEnabled(false);

    }

    private void achievements(){
        Intent i = new Intent(getApplicationContext(), activity_pop.class);
        startActivity(i);

    }


}


