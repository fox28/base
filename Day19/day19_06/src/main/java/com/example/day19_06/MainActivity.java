package com.example.day19_06;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.example.day19_06.R.id.textView;

public class MainActivity extends AppCompatActivity {
    TextView mtextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mtextView = (TextView) findViewById(textView);
    }

    public void onRotateXAndScale(View view) {
        final Animator animator = AnimatorInflater.loadAnimator(this, R.animator.rotatex_scale);
        animator.setTarget(mtextView);
        animator.start();
    }

    public void onRotateYAndScale(View view) {
        final Animator animator = AnimatorInflater.loadAnimator(this, R.animator.rotatey_scale);
        animator.setTarget(mtextView);
        animator.start();
    }
}
