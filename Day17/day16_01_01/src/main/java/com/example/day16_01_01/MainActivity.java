package com.example.day16_01_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("main", "MainActivity__onCreate");
        setContentView(R.layout.activity_main);
        Log.i("main", "MainActivity__onCreate__finish");
    }

    @Override
    protected void onStart() {
        Log.i("main", "MainActivity__onStart");
        super.onStart();
        Log.i("main", "MainActivity__onStart__finish");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("main", "MainActivity__onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("main", "MainActivity__onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i("main", "MainActivity__onStop");
    }

    @Override
    protected void onDestroy() {
        Log.i("main", "MainActivity__onDestroy");
        super.onDestroy();
        Log.i("main", "MainActivity__onDestroy__finish");

    }
}
