package com.example.day13_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.day13_01.bean.User;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 设置按钮onClick的响应单击事件
     *
     */
    public  void onClick(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
       /* intent.putExtra("name", "王菲");
        intent.putExtra("ages", 24);
        intent.putExtra("sex", "nv");*/
        User user1 = new User("张飞", "男", 23);
        intent.putExtra("user",  user1);

        startActivity(intent);

    }
}
