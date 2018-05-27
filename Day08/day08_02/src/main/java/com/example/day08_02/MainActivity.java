package com.example.day08_02;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Handler mHandler;
    ProgressBar mBar;
    TextView   mtvProgress;

    static final int DOWNLOAD_START = 0;
    static final int DOWNLOADING = 1;
    static final int DOWNLOAD_FINISH = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initHandler();

    }

    private void initHandler() {
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case DOWNLOAD_START:
                        Toast.makeText(MainActivity.this, "开始下载", Toast.LENGTH_SHORT).show();
                        break;
                    case DOWNLOADING:
                        mtvProgress.setText(msg.arg1+"%");
                        break;
                    case DOWNLOAD_FINISH:
                        Toast.makeText(MainActivity.this, "下载结束", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        };
    }

    private void initView() {
        mBar = (ProgressBar) findViewById(R.id.bar);
        mtvProgress = (TextView) findViewById(R.id.tvBar);

    }

    public void onClick(View view) {
        new Thread(){
            @Override
            public void run() {
                mHandler.sendEmptyMessage(DOWNLOAD_START);
                for (int i=1; i<=100; i++) {
//                    Message msg = new Message();
                    Message msg = Message.obtain(); // 利用消息池节省时间，（创建和回收message对象的时间）
                    msg.what = DOWNLOADING;
                    msg.arg1 = i;
                    mHandler.sendMessage(msg);
                    mBar.setProgress(i);
//                    mtvProgress.setText(i+ "%");
                    SystemClock.sleep(30);
                }
                mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
            }
        }.start();
    }
}
