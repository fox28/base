package com.example.day13_03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String name = bundle.getString("name");
        String sex = bundle.getString("性别");
        int age = bundle.getInt("年龄");

        Log.i("main", "name：" + name + " ,sex:" + sex + " ,age:" + age);
    }
}
