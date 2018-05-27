package com.example.day09_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.day09_02.bean.MessageBean;
import com.example.day09_02.utils.UrlUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    OkHttpClient mokHttpClient ;

    EditText metUserName, metNIck, metPassword, metConfirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        mokHttpClient = new OkHttpClient();


    }

    /**
     * 通过在布局中查找id，创建相关按钮对象
     */
    private void initView() {
        metUserName = (EditText) findViewById(R.id.etUserName);
        metNIck = (EditText) findViewById(R.id.etNick);
        metPassword = (EditText) findViewById(R.id.etPassword);
        metConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
    }

    /**
     * 响应"注册（post请求）"按钮的单击事件
     * @param view
     */
    public void registerByPost(View view) {

    }

    /**
     * 响应"注册（GET)"请求的单击事件
     * @param view
     */
    public void registerByGet(View view) {
        String userName = metUserName.getText().toString();
        String nick = metNIck.getText().toString();
        String password = metPassword.getText().toString();
//        String confirmPassword = metConfirmPassword.getText().toString();

        String url = new UrlUtils().url(I.SERVER_URL)
                .addParam(I.KEY_REQUEST, I.REQUEST_LOGIN)
                .addParam(I.User.USER_NAME, userName)
                .addParam(I.User.PASSWORD, password)
                .addParam(I.User.NICK, nick)
                .build();
        Request request = new Request.Builder().url(url).build();
        Call call = mokHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request,  IOException e) {
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
                final  MessageBean msg = new Gson().fromJson(json, MessageBean.class);
                if (msg.getMsg().equals("注册成功")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            Log.i("main", msg.toString());
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });
    }

    /**
     * 设置取消注册按钮的单击事件
     * @param view
     */
    public void unRegister(View view) {

    }


}
