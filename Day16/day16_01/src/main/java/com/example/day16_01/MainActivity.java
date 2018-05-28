package com.example.day16_01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    MyReceiver2 myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myReceiver = new MyReceiver2();
        IntentFilter filter = new IntentFilter("com.example.day16_01");
        registerReceiver(myReceiver, filter);

    }


    class MyReceiver2 extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // 定义接受
            String msg = intent.getStringExtra("msg");
            Log.i("main", "MyReceiver2收到的信息:"+msg);

        }
    }

    /**
     * 响应单击事件：发出广播
     * @param view
     */
    public void onClick(View view) {
        final Intent intent = new Intent("com.example.day16_01");
        intent.putExtra("msg", "Hello Android!");
        sendBroadcast(intent);

    }


}
