package com.example.day14_08;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Log.i("main", "Main2Activity.taskId:" + getTaskId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("main", "Main2Activity.onDestroy--taskId:" + getTaskId());
    }

    public void onStartMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
