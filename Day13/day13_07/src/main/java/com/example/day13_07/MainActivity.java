package com.example.day13_07;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.net.URI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 创建响应按钮onHome的单击事件
     * @param view
     */
    public void onHome(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    public void onBrowser(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.baidu.com"));
        startActivity(intent);
    }

    public void onDial(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:13055778899"));
        startActivity(intent);
    }

    public void onCall(View view) {

    }

    public void onSendMsg(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("sms:15158587123"));
        intent.putExtra("sms_body", "Hello Android:");
        startActivity(intent);
    }

    public void unInstall(View view) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:com.example.day13_06"));
        startActivity(intent);
    }

    public void install(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(dir, "baidu_safe.apk");
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }

    public void playMusic(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        File file = new File(dir, "see you see me.mp3");
        intent.setDataAndType(Uri.fromFile(file), "audio/mp3");
        startActivity(intent);

    }



}
