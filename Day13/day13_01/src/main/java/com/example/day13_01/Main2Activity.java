package com.example.day13_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.day13_01.bean.User;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        /*String name = intent.getStringExtra("name");
        String sex = intent.getStringExtra("sex");
        int age = intent.getIntExtra("age",18);
        Log.i("main", "姓名:" + name + ",性别：" + sex + ",年龄:" + age + "岁");*/

        User user = (User) intent.getSerializableExtra("user");

        Log.i("main", user.toString());


    }


}
