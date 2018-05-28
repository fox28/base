package com.example.day19_05;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView mtextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mtextView = (TextView) findViewById(R.id.textView);
    }

    public void onLeftIn(View view) {
        final Animator animator = AnimatorInflater.loadAnimator(this, R.animator.fade_left_in);
        animator.setTarget(mtextView);
        animator.start();
    }

    public void onLeftOut(View view) {
        final Animator animator = AnimatorInflater.loadAnimator(this, R.animator.fade_left_out);
        animator.setTarget(mtextView);
        animator.start();
    }

    public void onRightIn(View view) {
        final Animator animator = AnimatorInflater.loadAnimator(this, R.animator.fade_right_in);
        animator.setTarget(mtextView);
        animator.start();
    }

    public void onRightOut(View view) {
        final Animator animator = AnimatorInflater.loadAnimator(this, R.animator.fade_right_out);
        animator.setTarget(mtextView);
        animator.start();

    }

    public void onRightOutRotate(View view) {
        final Animator animator = AnimatorInflater.loadAnimator(this, R.animator.fade_right_out_rotate);
        animator.setTarget(mtextView);
        animator.start();
    }
}
