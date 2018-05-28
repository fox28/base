package com.example.day13_04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("main", "MainActivity.onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("main", "MainActivity.onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("main", "MainActivity.onResume()");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("main", "MainActivity.onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("main", "MainActivity.onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("main", "MainActivity.onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("main", "MainActivity.onRestart()");
    }

    /**
     *
     * @param view
     */
    public void onClick(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
