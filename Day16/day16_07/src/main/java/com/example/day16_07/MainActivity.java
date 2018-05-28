package com.example.day16_07;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View view) {
        final Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("下载apk");

//        Notification notify = builder.build();
        final NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        new Thread(){
            @Override
            public void run() {
                SystemClock.sleep(200);
                for (int i = 1; i <= 100; i++) {
                    builder.setProgress(100, i, false);
                    manager.notify(2,builder.build());
                    SystemClock.sleep(50);
                }

            }
        }.start();
    }
}
