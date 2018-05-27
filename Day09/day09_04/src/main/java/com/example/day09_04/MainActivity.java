package com.example.day09_04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.squareup.okhttp.OkHttpClient;

public class MainActivity extends AppCompatActivity {
    OkHttpClient mokHttpClient;
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

    /**
     * 响应"登陆get请求"的单击事件
     * @param view
     */
    public void loginByGet(View view) {
        String userName = metUserName.getText().toString();
        String password = metPassword.getText().toString();

    }


    /**
     * 响应"登陆post请求"的单击事件
     * @param view
     */
    public void loginByPost(View view) {
        String userName = metUserName.getText().toString();
        String password = metPassword.getText().toString();
    }

}
