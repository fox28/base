package com.example.day21_04;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.day21_04.fragment.BoutiqueFragment;
import com.example.day21_04.fragment.CartFragment;
import com.example.day21_04.fragment.CategoryFragment;
import com.example.day21_04.fragment.CollectFragment;
import com.example.day21_04.fragment.NewGoodsFragment;

public class MainActivity extends AppCompatActivity {
    ViewPager mViewPager;
    Fragment[] mFragmentArr;

    GoodsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();
        initView();

    }

    private void initFragment() {
        mFragmentArr = new Fragment[5];
        mFragmentArr[0] = new NewGoodsFragment();
        mFragmentArr[1] = new BoutiqueFragment();
        mFragmentArr[2] = new CategoryFragment();
        mFragmentArr[3] = new CartFragment();
        mFragmentArr[4] = new CollectFragment();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        mAdapter = new GoodsAdapter(getSupportFragmentManager(), mFragmentArr);
        mViewPager.setAdapter(mAdapter);
    }


    class GoodsAdapter extends FragmentPagerAdapter {
        Fragment[] fragments;
        public GoodsAdapter(FragmentManager fm, Fragment[] fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }



}
