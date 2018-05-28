package com.example.day16_05;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void sendNotify(View view) {
        // 创建通知构建者
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        // 设置通知的属性
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("亲,福利盗啦！")
                .setContentText("点击进入购物界面");
        // 创建通知的意图
        Intent intent = new Intent(this, BuyActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0,intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pi);

        // 构建通知
        Notification notify = builder.build();

        // 设置通知为手动清除
        notify.flags = Notification.FLAG_AUTO_CANCEL;

        // 设置通知管理器
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1,notify);

        /*

          //创建通知构建者
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        //设置通知的属性
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("亲，福利到啦!")
                .setContentText("点击进入购物页面");
        //创建通知的意图
        Intent intent = new Intent(this, BuyActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pi);
        //构建通知
        Notification notify = builder.build();

        //设置通知为手动清除
        notify.flags=Notification.FLAG_AUTO_CANCEL;
        //创建通知管理器
        NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1,notify);

         */

    }

    public void cancelNotify(View view) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(1);

    }
}
