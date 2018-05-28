package com.example.day14_05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("main", "MainActivity.taskId:" + getTaskId());
    }

    public void startMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("name", "张飞");
        startActivity(intent);
    }

    public void startMain2Activity(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String name = intent.getStringExtra("name");
        Log.i("main", "onNewIntent"+name);
    }
}
