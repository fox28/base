package com.example.day16_04;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver2 extends BroadcastReceiver {
    public MyReceiver2() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String msg = intent.getStringExtra("msg");
        Log.i("main", "MyReceiver2收到的信息：" + msg);
        if (isOrderedBroadcast()) {
            abortBroadcast(); // 关闭有序广播
        }
    }
}
