package com.example.day19_03;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView mivHorse;
    AnimationDrawable mAnimHorse;
    TranslateAnimation tAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mivHorse = (ImageView) findViewById(R.id.ivHorse);
        mAnimHorse = (AnimationDrawable) mivHorse.getBackground();
    }

    public void onStartPlay(View view) {
        mAnimHorse.start();
        /*Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        mivHorse.startAnimation(anim);*/

        tAnim = new TranslateAnimation(-130, 2050, 0, 0);
        tAnim.setDuration(5000);
        tAnim.setRepeatCount(5);
        mivHorse.startAnimation(tAnim);

    }

    public void onStopPlay(View view) {
        tAnim.cancel();
        mAnimHorse.stop();

    }
}
