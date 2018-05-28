package com.example.day14_03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import bean.User;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        Log.i("main", "Main2Activity.onCreate, getParcelableExtra(user)");
        User user = intent.getParcelableExtra("user");
        Log.i("main", user.toString());
    }
}
