package com.example.day08_03;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ProgressBar mBar;
    TextView mtvProgress;
    Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initHandler();
    }

    private void initHandler() {
        mHandler = new Handler();
    }

    private void initView() {
        mtvProgress = (TextView) findViewById(R.id.tvProgress);
        mBar = (ProgressBar) findViewById(R.id.bar);
    }

    public void onClick(View view) {
        new Thread(){
            int i;
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "开始下载", Toast.LENGTH_SHORT).show();
                    }
                });

                for(i=1; i<=100; i++) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mtvProgress.setText(i + "%");
                            mBar.setProgress(i);

                        }
                    });
                    SystemClock.sleep(30);
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "下载结束", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }.start();
    }
}
