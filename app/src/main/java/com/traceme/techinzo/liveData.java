package com.traceme.techinzo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.github.mikephil.charting.data.CandleEntry;

import java.util.ArrayList;
import java.util.Random;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class liveData extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int progress =50;
        valuesClass.values = new ArrayList<CandleEntry>();
        for (int i = 0; i < progress-1; i++) {
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
            long end1 = System.nanoTime() + 10000 * 1000;
            while (end1 > System.nanoTime()) { }

            float multi = (50 + 1);
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
           // CandleStickChartActivity.getInstance().finish();
        //    getApplicationContext()
          //          .startActivity(new Intent(getApplicationContext(),CandleStickChartActivity.class).addFlags(FLAG_ACTIVITY_NEW_TASK));
        }


        return super.onStartCommand(intent, flags, startId);
    }
}
