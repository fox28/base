package com.example.day15_03;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private int mCount = 1;

    public class MyBinder extends Binder {
        public int getCount() {
            return mCount++;
        }
    }
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        MyBinder myBinder = new MyBinder();
        Log.i("main", "MyService地址：" + myBinder.toString());
        return myBinder;
    }
}
