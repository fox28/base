package com.example.day09_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.day09_01.bean.UserBean;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    OkHttpClient mokHttpClient ;
    EditText metUserName,metPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        mokHttpClient = new OkHttpClient();
    }

    /**
     * 通过activity_main.xml布局中查找id，创建按钮对象
     */
    private void initView() {
        metUserName = (EditText) findViewById(R.id.etUserName);
        metPassword = (EditText) findViewById(R.id.etPassword);
    }

    /**
     * 响应loginByGet按钮的单击事件
     * @param view
     */
    public void loginByGet(View view) {
        String userName=metUserName.getText().toString();
        String password=metPassword.getText().toString();
        /*String url=I.SERVER_URL+"?request=login"
                +"&userName="+userName
                +"&password="+password;*/
        UrlUtils utils = new UrlUtils();
        String url = utils.url(I.SERVER_URL)
                .addParam(I.KEY_REQUEST, I.REQUEST_LOGIN)
                .addParam(I.User.USER_NAME, userName)
                .addParam(I.User.PASSWORD, password)
                .build();


        Request request = new Request.Builder().url(url).build();
        Call call = mokHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String json = response.body().string();
                final UserBean user = new Gson().fromJson(json, UserBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (user.getResult().equals("ok")) {
                            Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            Log.i("main", user.toString());
                        } else {
                            Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }



        });

    }

    /**
     * boginByPost的响应事件
     * @param view
     */
    public void loginByPost(View view) {
        String userName = metUserName.getText().toString();
        String password = metPassword.getText().toString();

        // 下面第二行，确实RequestBody实体类
/*
        //post请求的请求实体
        RequestBody requestBody=new FormBody.Builder()
                .add(I.KEY_REQUEST,I.REQUEST_LOGIN)
                .add(I.User.USER_NAME,userName)
                .add(I.User.PASSWORD,password)
                .build();
        Request request = new Request.Builder().url(I.SERVER_URL).post(requestBody).build();
        Call call = mokHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                final UserBean user = new Gson().fromJson(json, UserBean.class);
                if (user.getResult().equals("ok")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            Log.i("main", user.toString());
                        }
                    });
                }
            }
        });*/


    }
}
