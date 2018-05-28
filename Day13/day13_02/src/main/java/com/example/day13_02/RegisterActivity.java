package com.example.day13_02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.day13_02.R;
import com.example.day13_02.bean.User;

public class RegisterActivity extends AppCompatActivity {
    EditText etUserName, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

    }

    private void initView() {
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
    }

    /**
     * 创建Register按钮的响应事件
     * @param view
     */
    public void Register(View view) {
        String name = etUserName.getText().toString();
        String password = etPassword.getText().toString();

        User user = new User(name, password);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user", user);
        setResult(RESULT_OK, intent);// 调用setResult()设置返回值类型
        finish();


    }
}
