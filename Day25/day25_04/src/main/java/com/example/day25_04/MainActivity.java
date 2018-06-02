package com.example.day25_04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.day25_04.R;

public class MainActivity extends AppCompatActivity {
    TextView mtvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }



    public void login(View view) {
        setContentView(R.layout.layout_login);
        mtvTitle = (TextView) findViewById(R.id.tvTitle);
        mtvTitle.setText("登录");



    }
    public void register(View view) {
        setContentView(R.layout.layout_register);
        mtvTitle = (TextView) findViewById(R.id.tvTitle);
        mtvTitle.setText("注册");


    }
    public void onReturn(View view) {
        setContentView(R.layout.activity_main);
    }

}