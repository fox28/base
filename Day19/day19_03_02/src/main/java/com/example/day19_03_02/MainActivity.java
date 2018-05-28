package com.example.day19_03_02;

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

    public void onAlphaAndRotate(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha_rotate);
        mivTaiJi.startAnimation(animation);

    }

    public void onRotateAndTranslate(View view) {
        Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.rotate_translate);
        mivTaiJi.startAnimation(anim2);


    }
}
