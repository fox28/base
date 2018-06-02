package com.example.day22_03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("main", "MainActivity.dispatchTouch(). action:" + ev.getAction());
        super.dispatchTouchEvent(ev);
        return false;//
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("main", "MainActivity.onTouchEvent(). action:" + event.getAction());
        return super.onTouchEvent(event);
    }
}
