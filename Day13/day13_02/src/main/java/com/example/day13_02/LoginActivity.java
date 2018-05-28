package com.example.day13_02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.day13_02.bean.User;

public class LoginActivity extends AppCompatActivity {
    EditText etUserName, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);

    }

    /**
     *
     * @param view
     */
    public void login(View view) {
        String name = etUserName.getText().toString();
        String password = etPassword.getText().toString();

        User user = new User(name, password);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user", user);
        setResult(RESULT_OK, intent); // 调用方法setResult（） 设置返回的值
        finish();//关闭当前的Activity


    }
}
