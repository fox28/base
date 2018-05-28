package com.example.day13_04;

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
        Log.i("main", "2.onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("main", "2.onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("main", "2.onResume()");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("main", "2.onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("main", "2.onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("main", "2.onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("main", "2.onRestart()");
    }

    /**
     *
     * @param view
     */
    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
