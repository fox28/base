package com.example.day19_01;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView mivHorse;
    AnimationDrawable mAnimHorse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mivHorse = (ImageView) findViewById(R.id.ivHorse);
//        mivHorse.setBackgroundResource(R.drawable.runhorse);
        mAnimHorse = (AnimationDrawable) mivHorse.getBackground();
    }

    public void onStartPlay(View view) {
        mAnimHorse.start();
    }

    public void onStopPlay(View view) {
        mAnimHorse.stop();
    }


}
