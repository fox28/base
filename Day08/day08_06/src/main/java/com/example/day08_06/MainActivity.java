package com.example.day08_06;

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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startDownloadQuestion(View view) {
        // 1.创建请求的构建者对象
        Request.Builder builder = new Request.Builder();
        // 2.创建请求
        Request request = builder.url("").build();
        // 3.创建客户端
        OkHttpClient okHttpClient = new OkHttpClient();
        // 4.包含请求的任务
        Call call = okHttpClient.newCall(request);
        // 5. 创建任务放入请求列表
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                // 这是工作线程
            }

            @Override
            public void onResponse(Response response) throws IOException {
                // 这是工作线程
                String json = response.body().string();
//                Log.i("main", json);//日志窗口显示json格式数据
                Gson gson = new Gson();
                Question[] questions = gson.fromJson(json, Question[].class);
                for (Question q : questions) {
                    Log.i("main", q.toString());
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "考题下载成功了", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }
}
