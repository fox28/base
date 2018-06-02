package com.example.day22_ex01;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    SeekBarPro msbArrow1;
    SeekBarPro msbArrow2;
    int mStartY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        msbArrow1 = (SeekBarPro) findViewById(R.id.sbArrow1);
        msbArrow2 = (SeekBarPro) findViewById(R.id.sbArrow2);
    }

    public void onClick(View view) {
        new Thread(){
            @Override
            public void run() {

                for (int i = 1; i <= 100; i++) {
                    msbArrow1.setProgress(i);
                    msbArrow2.setProgress(i);
                    SystemClock.sleep(30);

                }


            }
        }.start();
    }
}
