package com.example.day19_07;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
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

    public void onGo(View view) {
        final Resources res= getResources();
        final int width = res.getDisplayMetrics().widthPixels;
        final int height = res.getDisplayMetrics().heightPixels;
        final ViewPropertyAnimator animate = mivTaiJi.animate();
        animate.x(width-mivTaiJi.getWidth())
                .y(height/2)
                .rotationX(360)
                .rotationY(360)
                .setDuration(3000)
                .alpha(0)
                .scaleX(0.2f)
                .scaleY(0.2f)
                .start();

    }

    public void onGoBack(View view) {
        Resources res = getResources();
        int width = res.getDisplayMetrics().widthPixels;
        int height = res.getDisplayMetrics().heightPixels;
        final ViewPropertyAnimator animate = mivTaiJi.animate();
        animate.x(mivTaiJi.getWidth()+300)
                .y(mivTaiJi.getHeight()+300)
                .rotationX(360)
                .rotationY(360)
                .setDuration(3000)
                .alpha(1)
                .scaleX(2f)
                .scaleY(2f)
                .start();


    }
}
