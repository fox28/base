package com.example.day17_05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ExpireJobFragment.IExpireJob {
    Resume mResume;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initResume();
    }

    private void initResume() {
        // Resume(String property, String expirJob, String address, String salary)
        mResume = new Resume("全全职","Android工程师", "朝阳区", "8--12K");
    }

    @Override
    public Resume getResume() {
        return mResume;
    }

    @Override
    public void setResume(Resume resume) {
        mResume = resume;
    }
}
