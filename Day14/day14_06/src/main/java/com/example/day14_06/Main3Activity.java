package com.example.day14_06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Log.i("main", "Main3Activity被启动：" + getTaskId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("main", "Main3Activity.onDestroy" + getTaskId());
    }

    public void destroyAllActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("close", true);
        intent.putExtra("msg", "我要去周游世界");
        startActivity(intent);
    }
}
