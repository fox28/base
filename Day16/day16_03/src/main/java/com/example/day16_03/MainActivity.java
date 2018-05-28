package com.example.day16_03;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    static final String START_ACTIVITY = "start_activity";
    static final String START_SERVICE = "start_service";
    static final String SEND_BROADCAST = "send_broadcast";

    MyReceiver2 myReceiver2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 实例化广播接受者
        myReceiver2 = new MyReceiver2();
        IntentFilter filter = new IntentFilter();
        filter.addAction(START_ACTIVITY);
        filter.addAction(START_SERVICE);
        filter.addAction(SEND_BROADCAST);
        registerReceiver(myReceiver2, filter);


    }

    public void onStartActivity(View view) {
        final Intent intent = new Intent(START_ACTIVITY);
        sendBroadcast(intent);

    }

    public void onStartService(View view) {
        final Intent intent = new Intent(START_SERVICE);
        sendBroadcast(intent);
    }

    public void onSendBroadcast(View view) {
        final Intent intent = new Intent(SEND_BROADCAST);
        intent.putExtra("msg", "onSendBroadcast发出的消息");
        sendBroadcast(intent);
    }

    class MyReceiver2 extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Intent intent1 = null;
            switch (action) {
                case START_ACTIVITY:
                    intent1 = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(intent1);
                    break;
                case START_SERVICE:
                    intent1 = new Intent(MainActivity.this, MyService.class);
                    startService(intent1);
                    break;
                case SEND_BROADCAST:
                    intent1 = new Intent(SEND_BROADCAST+2);
                    intent1.putExtra("msg", "MyReceiver2发出的消息");
                    sendBroadcast(intent1);
                    break;
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myReceiver2 != null) {
            unregisterReceiver(myReceiver2);
        }
    }
}
