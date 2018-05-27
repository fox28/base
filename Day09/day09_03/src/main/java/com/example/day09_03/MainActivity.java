package com.example.day09_03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.day09_03.bean.UserBean;
import com.example.day09_03.utils.UrlUtils;
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

    /**
     * 响应"下载好友get请求"按钮的单击事件
     * @param view
     */
    public void onClick(View view) {
        String url = new UrlUtils().url(I.SERVER_URL)
                .addParam(I.KEY_REQUEST, I.REQUEST_DOWNLOAD_CONTACT_LIST)
                .addParam(I.User.USER_NAME, "a")
                .addParam(I.PAGE_ID, "0")
                .addParam(I.PAGE_SIZE, "10")
                .build();
        Request request = new Request.Builder().url(url).build();
        OkHttpClient client=new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(MainActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String json = response.body().string();
                UserBean[] userBeans = new Gson().fromJson(json, UserBean[].class);
                for (UserBean userBean : userBeans) {
                    Log.i("main", userBean.toString());
                }


            }
        });

    }
}
