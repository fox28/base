package com.example.day15_01;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("main", "onCreate被启动");
    }

    // jdk2.0之后使用
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("main", "onStartCommand被启动");
        return super.onStartCommand(intent, flags, startId);

    }

    // jdk2.0之前使用
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("main", "onDestroy被启动");

    }
}
