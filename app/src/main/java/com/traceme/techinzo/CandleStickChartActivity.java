package com.traceme.techinzo;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.content.ContextCompat;

import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class CandleStickChartActivity extends DemoBase implements OnSeekBarChangeListener {

    private CandleStickChart chart;
    private SeekBar seekBarX, seekBarY;
    private TextView tvX, tvY;
    private int minToMill=900000;

    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_candle_stick_chart);
      //  Toast.makeText(this,String.valueOf(valuesClass.close),Toast.LENGTH_SHORT).show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Write whatever to want to do after delay specified (1 sec)
                if(valuesClass.time<50){
                    if(valuesClass.values.get(valuesClass.time).getClose()>valuesClass.predict){
                        Toast.makeText(getApplicationContext(),"You win",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"You Lose",Toast.LENGTH_LONG).show();
                    }
                }
            }

        }, i*1000);


        set();
    }
    void set(){
        setTitle("CandleStickChartActivity");

        tvX = findViewById(R.id.tvXMax);
        tvY = findViewById(R.id.tvYMax);

        seekBarX = findViewById(R.id.seekBar1);
        seekBarX.setOnSeekBarChangeListener(this);
        //  seekBarX.setMax(96);
        seekBarY = findViewById(R.id.seekBar2);
        seekBarY.setOnSeekBarChangeListener(this);

        chart = findViewById(R.id.chart1);
        chart.setBackgroundColor(Color.WHITE);
        chart.setMaxVisibleValueCount(1);
        chart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no valuesClass will be
        // drawn
        chart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(true);

        chart.setDrawGridBackground(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setAxisMaximum(51);
        YAxis leftAxis = chart.getAxisLeft();

//        leftAxis.setEnabled(false);
        leftAxis.setLabelCount(7, false);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawAxisLine(false);
        YAxis rightAxis = chart.getAxisRight();

        //       rightAxis.setEnabled(false);

//        rightAxis.setStartAtZero(false);

        // setting data
        seekBarX.setProgress(50);
        seekBarY.setProgress(50);

        chart.getLegend().setEnabled(false);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        valuesClass.values = new ArrayList<CandleEntry>();

        progress = (seekBarX.getProgress());

        tvX.setText(String.valueOf(progress));
        tvY.setText(String.valueOf(seekBarY.getProgress()));

        chart.resetTracking();

     //   ArrayList<CandleEntry> values = new ArrayList<>();
        valuesClass.values.add(new CandleEntry(0,70,10,valuesClass.open,55,
                getResources().getDrawable(R.drawable.star)));

        for ( i = 1; i < progress-1; i++) {
            long time = System.currentTimeMillis();
            long time2 = System.currentTimeMillis();

            /*      while (time+minToMill>time2){

                time2 = System.currentTimeMillis();

                float multi = (seekBarY.getProgress() + 1);
                float val = (float) (Math.random() * 40) + multi;

                float high = (float) (Math.random() * 9) + 8f;
                float low = (float) (Math.random() * 9) + 8f;

                float open = (float) (Math.random() * 6) + 1f;
                float close = (float) (Math.random() * 6) + 1f;
                Random random = new Random();
                boolean even = i % 2 == 0;
                int rand= random.nextInt(3);
                if(rand%2==0){
                    even=true;
                }else {
                    even=false;
                }

                values.add(new CandleEntry(
                        i, val + high,
                        val - low,
                        even ? val + open : val - open,
                        even ? val - close : val + close,
                        getResources().getDrawable(R.drawable.star)
                ));
                callThis(values);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

*/
            float multi = (seekBarY.getProgress() + 1);
            float val = (float) (Math.random() * 40) + multi;

            float high = (float) (Math.random() * 9) + 8f;
            float low = (float) (Math.random() * 9) + 8f;

            float open = (float) (Math.random() * 6) + 1f;
            float close = (float) (Math.random() * 6) + 1f;
            Random random = new Random();
            boolean even = i % 2 == 0;
            int rand= random.nextInt(3);
            if(rand%2==0){
                even=true;
            }else {
                even=false;
            }

            valuesClass.values.add(new CandleEntry(
                    i, val + high,
                    val - low,
                    even ? val + open : val - open,
                    even ? val - close : val + close,
                    getResources().getDrawable(R.drawable.star)
            ));

            callThis(valuesClass.values);


        }
  //      valuesClass.values.add(new CandleEntry(progress-1,70,10,50,valuesClass.close,
    //            getResources().getDrawable(R.drawable.star)));

        callThis(valuesClass.values);


    }


    void callThis(ArrayList<CandleEntry> values){
        chart.resetTracking();
        CandleDataSet set1 = new CandleDataSet(values, "Data Set");

        set1.setDrawIcons(false);
        set1.setAxisDependency(AxisDependency.LEFT);
//        set1.setColor(Color.rgb(80, 80, 80));
        set1.setShadowColor(Color.DKGRAY);
        set1.setShadowWidth(0.7f);
        set1.setDecreasingColor(Color.RED);
        set1.setDecreasingPaintStyle(Paint.Style.FILL);
        set1.setIncreasingColor(Color.rgb(0, 255, 0));
        set1.setIncreasingPaintStyle(Paint.Style.FILL);
        set1.setNeutralColor(Color.BLUE);
        //set1.setHighlightLineWidth(1f);

        CandleData data = new CandleData(set1);
        chart.animateX(1000*50);
        chart.setData(data);
        chart.invalidate();
        chart.destroyDrawingCache();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.candle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.viewGithub: {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://github.com/PhilJay/MPAndroidChart/blob/master/MPChartExample/src/com/xxmassdeveloper/mpchartexample/CandleStickChartActivity.java"));
                startActivity(i);
                break;
            }
            case R.id.actionToggleValues: {
                for (IDataSet set : chart.getData().getDataSets())
                    set.setDrawValues(!set.isDrawValuesEnabled());

                chart.invalidate();
                break;
            }
            case R.id.actionToggleIcons: {
                for (IDataSet set : chart.getData().getDataSets())
                    set.setDrawIcons(!set.isDrawIconsEnabled());

                chart.invalidate();
                break;
            }
            case R.id.actionToggleHighlight: {
                if(chart.getData() != null) {
                    chart.getData().setHighlightEnabled(!chart.getData().isHighlightEnabled());
                    chart.invalidate();
                }
                break;
            }
            case R.id.actionTogglePinch: {
                if (chart.isPinchZoomEnabled())
                    chart.setPinchZoom(false);
                else
                    chart.setPinchZoom(true);

                chart.invalidate();
                break;
            }
            case R.id.actionToggleAutoScaleMinMax: {
                chart.setAutoScaleMinMaxEnabled(!chart.isAutoScaleMinMaxEnabled());
                chart.notifyDataSetChanged();
                break;
            }
            case R.id.actionToggleMakeShadowSameColorAsCandle: {
                for (ICandleDataSet set : chart.getData().getDataSets()) {
                    ((CandleDataSet) set).setShadowColorSameAsCandle(!set.getShadowColorSameAsCandle());
                }

                chart.invalidate();
                break;
            }
            case R.id.animateX: {
                chart.animateX(2000);
                break;
            }
            case R.id.animateY: {
                chart.animateY(2000);
                break;
            }
            case R.id.animateXY: {
                chart.animateXY(2000, 2000);
                break;
            }
            case R.id.actionSave: {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    saveToGallery();
                } else {
                    requestStoragePermission(chart);
                }
                break;
            }
        }
        return true;
    }

    @Override
    protected void saveToGallery() {
        saveToGallery(chart, "CandleStickChartActivity");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}
}