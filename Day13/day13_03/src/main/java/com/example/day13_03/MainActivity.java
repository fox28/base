package com.example.day13_03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        Bundle bundle = new Bundle();
        bundle.putString("name", "张飞");
        bundle.putString("性别", "男");
        bundle.putInt("年龄", 28);

        intent.putExtras(bundle);
        startActivity(intent);




    }
}
