package com.example.day17_06;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import cn.ucai.day17_06.Resume;

public class MainActivity extends AppCompatActivity {
    Resume mResume;
    ExpireJobFragment mExpireJobFragment;
    ImageView mivExpand;

    // 期望职位的fragment是否展开显示
    boolean misExpand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initResume();
        initView();
        setListener();
    }

    private void setListener() {
        mivExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                if (!misExpand) {
                    mExpireJobFragment = (ExpireJobFragment) manager.findFragmentByTag("expirejob_fragment");
                    if (mExpireJobFragment == null) {
                        mExpireJobFragment = new ExpireJobFragment();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("resume", mResume);
                        mExpireJobFragment.setArguments(bundle);
                        ft.add(R.id.layout_expire_job, mExpireJobFragment, "expirejob_fragment").commit();

                    } else {
                        ft.show(mExpireJobFragment).commit();
                    }
                    mivExpand.setImageResource(R.drawable.expand_off);
                } else {
                    ft.hide(mExpireJobFragment).commit();
                    mivExpand.setImageResource(R.drawable.expand_on);

                }
                misExpand = !misExpand;
            }
        });
    }

    private void initView() {
        mivExpand = (ImageView) findViewById(R.id.ivExpand);
    }


    private void initResume() {
        // Resume(String expireJob, String workAddress, String salary, String property)
        mResume = new Resume("Android开发工程师", "海淀区北大附件", "8——11K", "全全职");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi_salary:
                mResume.setSalary("60-100K");
                break;
            case R.id.mi_address:
                mResume.setWorkAddress("南大街南大街");
                break;
        }
        sendBroadcast(new Intent("update_resume").putExtra("resume", mResume));
        return super.onOptionsItemSelected(item);
    }


    /*



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi_address:
                mResume.setWorkAddress("武汉");
                break;
            case R.id.mi_salary:
                mResume.setSalary("6-10k");
                break;
        }
        sendBroadcast(new Intent("update_resume").putExtra("resume",mResume));
        return super.onOptionsItemSelected(item);
    }
     */
}
