package com.example.day15_06.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.day15_06.MyApplication;
import com.example.day15_06.bean.ApkBean;
import com.example.day15_06.utils.ApkUtils;
import com.example.day15_06.utils.OkUtils;

public class ServiceCheckApkVersion extends Service {
    public ServiceCheckApkVersion() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        OkUtils<ApkBean> utils = new OkUtils<>(this);
        utils.url(MyApplication.ROOT_URL + MyApplication.APK_INFO)
                .targetClass(ApkBean.class)
                .execute(new OkUtils.OnCompleteListener<ApkBean>() {
                    @Override
                    public void onSuccess(ApkBean result) {
//                        Log.i("main", result.toString());

                        // 当前apk的版本
                        int apkVersion = ApkUtils.getApkVersion(ServiceCheckApkVersion.this);

                        if (result.getVersion() > apkVersion) {
                            // 启动Service，这个Service的功能是下载新版apk
                            Intent intent2 = new Intent(ServiceCheckApkVersion.this, ServiceDownloadApk.class);
                            intent2.putExtra("apk_filename", result.getApk());
                            startService(intent2);

                        }
                    }

                    @Override
                    public void onError(String error) {
                        Log.i("main", error);
                    }
                });


        return super.onStartCommand(intent, flags, startId);
    }
}
