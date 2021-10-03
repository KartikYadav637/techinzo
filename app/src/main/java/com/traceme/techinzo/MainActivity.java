package com.traceme.techinzo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.mikephil.charting.data.CandleEntry;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText open,close,predict,time;
    Button enter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        open = findViewById(R.id.openPrice);
        close = findViewById(R.id.closePrice);
        enter = findViewById(R.id.passvalue);
        predict = findViewById(R.id.value);
        time = findViewById(R.id.time);
        Intent i = new Intent(this, CandleStickChartActivity.class);


        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(open.getText().toString()!=null&&close.getText().toString()!=null){
                    valuesClass.open = Float.parseFloat(open.getText().toString());
                    valuesClass.close = Float.parseFloat(close.getText().toString());
                    valuesClass.predict = Float.parseFloat(predict.getText().toString());
                    valuesClass.time = Integer.parseInt(time.getText().toString());
            //        startService(new Intent(getApplicationContext(),liveData.class));
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"Enter Correct Value",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}