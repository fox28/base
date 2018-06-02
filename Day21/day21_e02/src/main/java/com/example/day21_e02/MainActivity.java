package com.example.day21_e02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.day21_e02.widget.FlowIndicator;
import com.example.day21_e02.widget.SlideLoopView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> mArrayList;

    SlideLoopView mSlideLoopView;
    FlowIndicator mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initView() {
        mSlideLoopView = (SlideLoopView) findViewById(R.id.slideLoopView);
        mIndicator = (FlowIndicator) findViewById(R.id.indicator);
    }

    private void initData() {
        mArrayList = new ArrayList<>();
        mArrayList.add(R.drawable.goods01);
        mArrayList.add(R.drawable.goods02);
        mArrayList.add(R.drawable.goods03);
        mArrayList.add(R.drawable.goods04);
        mArrayList.add(R.drawable.goods05);
    }

    public void onStartPlay(View view) {
        mSlideLoopView.startPlay(this,mIndicator,mArrayList);
    }

    public void onStopPlay(View view) {
        mSlideLoopView.stopPlay();
    }
}
