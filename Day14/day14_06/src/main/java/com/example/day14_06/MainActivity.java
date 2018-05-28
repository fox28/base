package com.example.day14_06;

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

    public void startMain2Activity(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    public void startSelf(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("main", "MainActivity.onNewIntent:"+getTaskId());

        boolean isClose = intent.getBooleanExtra("close", false);

        if (isClose) {
            String msg = intent.getStringExtra("msg");
            Log.i("main", msg);
            finish();
            Log.i("main","去吧，少年。");

        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("main", "MainActivity.onDestroy" + getTaskId());
    }
}
