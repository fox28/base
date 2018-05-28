package com.example.day14_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


/**
 * 横竖屏切换，观察方法执行熟悉
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("main","onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("main","onStart");

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("main","onRestoreInstanceState");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("main","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("main","onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("main","onSaveInstanceState");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("main","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("main","onDestroy");
    }
}
