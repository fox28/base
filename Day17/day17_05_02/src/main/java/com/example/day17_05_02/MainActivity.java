package com.example.day17_05_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ExpireJobFragment.IExpireJob {
    private Resume mResume;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initResume();
    }

    private void initResume() {
        mResume = new Resume("全职", "Android开发工程师", "北京北京", "8-15k");
    }


    @Override
    public Resume getResume() {
        return mResume;
    }

    @Override
    public void setResume(Resume resume) {
        mResume=resume;
    }
}
