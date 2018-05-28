package com.example.day19_02;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView mivTaiJi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mivTaiJi = (ImageView) findViewById(R.id.ivTaiJi);
    }

    public void onAlpha(View view) {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        mivTaiJi.startAnimation(anim);
    }


    public void onScale(View view) {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.scale);
        mivTaiJi.startAnimation(anim);
    }

    public void onRotate(View view) {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.rotate);
        mivTaiJi.startAnimation(anim);
    }

    public void onTranslate(View view) {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        mivTaiJi.startAnimation(anim);
    }
}
