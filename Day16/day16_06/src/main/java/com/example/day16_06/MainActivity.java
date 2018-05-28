package com.example.day16_06;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
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
                .setContentTitle("启动Service")
                .setContentText("点我启动Service");

        Intent intent = new Intent(this, MyService.class);
        PendingIntent pi = PendingIntent.getService(this,0, intent,PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pi);

        Notification notify = builder.build();

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(2,notify);




    }
}
