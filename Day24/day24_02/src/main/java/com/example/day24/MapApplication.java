package com.example.day24;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by apple on 2017/3/20.
 */

public class MapApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
    }
}
