package com.example.day22_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        findViewById(R.id.textView).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                StringBuilder sb = new StringBuilder();
                sb.append("action:").append(event.getAction()).append(",\n")
                        .append("X:").append(event.getX()).append(",\n")
                        .append("rawX:").append(event.getRawX()).append(",\n")
                        .append("pressure").append(event.getPressure()).append(",\n")
                        .append("size:").append(event.getSize()).append(",\n")
                        .append("time:").append(event.getDownTime());

                Log.i("main", sb.toString());
                return true;
            }
        });
    }
}
