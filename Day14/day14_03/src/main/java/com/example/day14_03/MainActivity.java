package com.example.day14_03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import bean.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, Main2Activity.class);
        Log.i("main", "MainActivity.onCreate, intent.putEntra(user)");
        User user = new User("张飞", "男", 23);
        intent.putExtra("user", user);
        startActivity(intent);

    }
}
