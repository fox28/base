package com.example.day08_ex01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import bean.Job;

public class MainActivity extends AppCompatActivity {
    final String WebSite = "http:/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 响应"开始下载"按钮的单击事件
     * @param view 被点击的view 实际就是"开始下载"按钮
     */
    public void startDownloadJob(View view) {
        // 创建请求
        Request request = new Request.Builder().url(WebSite).build();
        // 创建客户端
        OkHttpClient okHttpClient = new OkHttpClient();
        // 包含请求的任务
        Call call = okHttpClient.newCall(request);
        // 4.创建任务放入请求列表
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "下载职位失败", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String json = response.body().string();
                Job[] jobArr = new Gson().fromJson(json, Job[].class);
                for (Job job : jobArr) {
                    Log.i("main", job.toString());
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "下载职位成功", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }
}
