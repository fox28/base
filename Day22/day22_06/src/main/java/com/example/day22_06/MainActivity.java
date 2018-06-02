package com.example.day22_06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    float mScaleFactor = 1;
    ImageView mivTaiJi;

    ScaleGestureDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        mDetector = new ScaleGestureDetector(this, new MyScaleGestureListener());
        setListener();


    }

    private void setListener() {
        mivTaiJi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDetector.onTouchEvent(event);
                return true;
            }
        });
    }

    private void initView() {
        mivTaiJi = (ImageView) findViewById(R.id.ivTaiJi);
    }

    class MyScaleGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();
            mScaleFactor = Math.min(2, Math.max(mScaleFactor, 0.5f));
            final ViewPropertyAnimator animate = mivTaiJi.animate();
            animate.scaleX(mScaleFactor)
                    .scaleY(mScaleFactor)
                    .setDuration(500)
                    .start();

            return super.onScale(detector);
        }
    }
}
