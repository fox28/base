package com.example.day18_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    ScreenView mScreenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }


    // 实例化按钮
    private void initView() {
        mScreenView = (ScreenView) findViewById(R.id.screenView);
    }

    public void onClick(View view) {
        mScreenView.setWallPageId(R.drawable.bg02);
    }
}
