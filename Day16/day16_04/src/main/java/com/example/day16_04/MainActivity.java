package com.example.day16_04;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MyReceiver3 myReceiver3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myReceiver3 = new MyReceiver3();
        IntentFilter filter = new IntentFilter("com.example.day16_04");
        filter.setPriority(600);
        registerReceiver(myReceiver3, filter);
    }


    public void onClick(View view) {
        final Intent intent = new Intent("com.example.day16_04");
        intent.putExtra("msg", "hello world!");
        sendOrderedBroadcast(intent,null);
    }

    class MyReceiver3 extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra("msg");
            Log.i("main", "MyReceiver3收到的信息：" + msg);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myReceiver3 != null) {
            unregisterReceiver(myReceiver3);
        }
    }
}
