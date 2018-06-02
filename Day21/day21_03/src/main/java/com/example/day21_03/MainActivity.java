package com.example.day21_03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.day21_03.widget.FlowIndicator;
import com.example.day21_03.widget.SlideLoopView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> mGoodsList;

    SlideLoopView mSlideLoopView;
    FlowIndicator mFlowIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

    }

    private void initData() {
        mGoodsList = new ArrayList<>();
        mGoodsList.add(R.drawable.goods01);
        mGoodsList.add(R.drawable.goods02);
        mGoodsList.add(R.drawable.goods03);
        mGoodsList.add(R.drawable.goods04);
        mGoodsList.add(R.drawable.goods05);
    }

    private void initView() {
        mSlideLoopView = (SlideLoopView) findViewById(R.id.slView);
        mFlowIndicator = (FlowIndicator) findViewById(R.id.flowIndicator);
    }

    public void startPlay(View view) {
        mSlideLoopView.startPlay(this, mFlowIndicator,mGoodsList);
    }

    public void stopPlay(View view) {
        mSlideLoopView.stopPlay();
    }

}
