package com.example.day15_06.service;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.example.day15_06.MyApplication;
import com.example.day15_06.utils.OkUtils;

import java.io.File;

public class ServiceDownloadApk extends Service {
    public ServiceDownloadApk() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        final String filename = intent.getStringExtra("apk_filename");
        final File file = new File(dir, filename); // 创建保存至sd卡的File对象

        OkUtils<Message> utils = new OkUtils<>(this);
        utils.url(MyApplication.ROOT_URL+filename)
                .downloadFile(file)
                .execute(new OkUtils.OnCompleteListener<Message>() {
                    @Override
                    public void onSuccess(Message msg) {
                        if (msg.what == OkUtils.DOWNLOAD_FINISH) {
                            Log.i("main", filename + "下载成功");
                            // 安装apk
                            Intent intentInstall = new Intent(Intent.ACTION_VIEW);
                            intentInstall.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                            startActivity(intentInstall);


                            /*
                             Intent intent = new Intent(Intent.ACTION_VIEW);
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(dir, "baidu_safe.apk");
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
                             */
                        }

                    }

                    @Override
                    public void onError(String error) {

                    }
                });

        return super.onStartCommand(intent, flags, startId);
    }
}
