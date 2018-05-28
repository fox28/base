package com.example.day13_02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.day13_02.bean.User;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_LOGIN = 0;
    static final int REQUEST_REGISTER = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btnLogin:
                intent = new Intent(this, LoginActivity.class);
                startActivityForResult(intent, REQUEST_LOGIN);
                break;
            case R.id.btnRegister:
                intent = new Intent(this, RegisterActivity.class);
                startActivityForResult(intent, REQUEST_REGISTER);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUEST_LOGIN:
                User userLogin = (User) intent.getSerializableExtra("user");
                Log.i("main", "登录操作返回结果：" + userLogin.toString());
                break;
            case REQUEST_REGISTER:
                User userRegister = (User) intent.getSerializableExtra("user");
                Toast.makeText(this, "注册操作返回结果："+userRegister.toString(), Toast.LENGTH_SHORT).show();

                break;
        }


    }
}
