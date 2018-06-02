package com.example.day24;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by apple on 2017/3/21.
 */

public class MapApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 全局sdk初始化
        SDKInitializer.initialize(this);
    }
}
