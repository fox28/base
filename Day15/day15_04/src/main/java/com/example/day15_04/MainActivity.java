package com.example.day15_04;

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

        Log.i("mian", "MainActivity.onCreate");
    }

    public void onClick(View view) {
        Log.i("main", "onClick");
        Intent intent = new Intent(this, MyService.class);
        startService(intent);

    }
}
