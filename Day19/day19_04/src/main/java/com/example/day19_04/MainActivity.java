package com.example.day19_04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView mlistView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        mlistView = (ListView) findViewById(R.id.listView);
    }

    public void onRotate(View view) {
        RotateAnimation anim = new RotateAnimation(0, 720, mlistView.getWidth() / 2, mlistView.getHeight() / 2);
        anim.setDuration(2000);
        mlistView.startAnimation(anim);
    }

    public void onAlpha(View view) {
        AlphaAnimation anim = new AlphaAnimation(0, 1);
        anim.setDuration(2000);
        mlistView.startAnimation(anim);
    }

    public void onScale(View view) {
         ScaleAnimation animation = new ScaleAnimation(1/2, 2, 1/2, 3,
                mlistView.getWidth() / 2, mlistView.getHeight() / 2);
         animation.setDuration(2000);
        mlistView.startAnimation(animation);
    }
}

