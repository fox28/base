package com.example.day15_02;

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
        Log.i("main", "onBind");
        return  null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("main", "MyService.onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("main", "MyService.onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("main", "onUnbind");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i("main", "onRebind");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("main", "onDestroy");
    }
}
