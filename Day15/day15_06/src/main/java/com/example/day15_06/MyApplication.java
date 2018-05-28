package com.example.day15_06;

import android.app.Application;
import android.content.Intent;

import com.example.day15_06.service.ServiceCheckApkVersion;

/**
 * Created by apple on 2017/3/6.
 */

public class MyApplication extends Application {
    public static final String ROOT_URL = "http://10.0.2.2/";
    public static final String APK_INFO = "apk_info.json";

    @Override
    public void onCreate() {
        super.onCreate();
        // 启动检查apk版本的service
        Intent intent = new Intent(this, ServiceCheckApkVersion.class);
        startService(intent);
    }
}
