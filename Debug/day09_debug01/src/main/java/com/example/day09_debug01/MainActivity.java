package com.example.day09_debug01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText metUserName, metPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        metUserName = (EditText) findViewById(R.id.etUserName);
        metPassword = (EditText) findViewById(R.id.etPassword);
    }

    public void onLoginByGet(View view) {
        String userName = metUserName.getText().toString();
        String password = metPassword.getText().toString();
    }
/*
        String url = new UrlUtils()
                .addParam(I.KEY_REQUEST, I.REQUEST_LOGIN)
                .addParam(I.User.USER_NAME, userName)
                .addParam(I.User.PASSWORD, password)
                .build();
        final Request request = new Request.Builder().url(url).build();
        OkHttpClient client = new OkHttpClient();
        final Call call = client.newCall(request);
        call.enqueue(getResponse());
    }



    private Callback getResponse() {
        return new Callback() {
            @Override
            public void onResponse(Response response) throws IOException {
                //服务端响应成功回调
                String json = response.body().string();
                Gson gson = new Gson();
                final UserBean user = gson.fromJson(json, UserBean.class);
                if (user != null) {
                    Toast.makeText(MainActivity.this, "登陆成功",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "登陆失败",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(MainActivity.this, e.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        };
    }*/



}
