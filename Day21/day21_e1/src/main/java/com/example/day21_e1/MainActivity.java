package com.example.day21_e1;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.day21_e1.fragment.BoutiqueFragment;
import com.example.day21_e1.fragment.CartFragment;
import com.example.day21_e1.fragment.CategoryFragment;
import com.example.day21_e1.fragment.CollectFragment;
import com.example.day21_e1.fragment.NewGoodsFragment;
import com.example.day21_e1.widget.FlowIndicator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Fragment[] mFragments;
    ViewPager mvpGoods;
    TextView  mtvP1, mtvP2, mtvP3, mtvP4, mtvP5;

    GoodsAdapter mAdapter;

    FlowIndicator mFlowIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();
        initView();

        setListener();
    }

    private void setListener() {
        mtvP1.setOnClickListener(this);
        mtvP2.setOnClickListener(this);
        mtvP3.setOnClickListener(this);
        mtvP4.setOnClickListener(this);
        mtvP5.setOnClickListener(this);

        setOnPageChangeListener();


    }

    private void setOnPageChangeListener() {
        mvpGoods.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                initDrawable();
                switch (position) {
                    case 0:
                        setDrawable(mtvP1,  Color.rgb(0xff, 0x00, 0xff));
                        mFlowIndicator.setFocus(0);
                        break;
                    case 1:
                        setDrawable(mtvP2, Color.rgb(0xff, 0x00, 0xff));
                        mFlowIndicator.setFocus(1);
                        break;
                    case 2:
                        setDrawable(mtvP3,Color.rgb(0xff, 0x00, 0xff));
                        mFlowIndicator.setFocus(2);
                        break;
                    case 3:
                        setDrawable(mtvP4,Color.rgb(0xff, 0x00, 0xff));
                        mFlowIndicator.setFocus(3);
                        break;
                    case 4:
                        setDrawable(mtvP5, Color.rgb(0xff, 0x00, 0xff));
                        mFlowIndicator.setFocus(4);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        mvpGoods = (ViewPager) findViewById(R.id.vpGoods);

        mAdapter = new GoodsAdapter(getSupportFragmentManager(), mFragments);
        mvpGoods.setAdapter(mAdapter);

        mtvP1 = (TextView) findViewById(R.id.tvP1);
        mtvP2 = (TextView) findViewById(R.id.tvP2);
        mtvP3 = (TextView) findViewById(R.id.tvP3);
        mtvP4 = (TextView) findViewById(R.id.tvP4);
        mtvP5 = (TextView) findViewById(R.id.tvP5);
        mFlowIndicator = (FlowIndicator) findViewById(R.id.flowIndicator);
        mFlowIndicator.setCount(mFragments.length);


    }

    private void initFragment() {
        mFragments = new Fragment[5];
        mFragments[0] = new NewGoodsFragment();
        mFragments[1] = new BoutiqueFragment();
        mFragments[2] = new CategoryFragment();
        mFragments[3] = new CartFragment();
        mFragments[4] = new CollectFragment();

    }

    @Override
    public void onClick(View v) {
        initDrawable();
        switch (v.getId()) {
            case R.id.tvP1:
                setDrawable(mtvP1,Color.rgb(0xff,0x00,0xff));
                mvpGoods.setCurrentItem(0);
                mFlowIndicator.setFocus(0);
                break;
            case R.id.tvP2:
                setDrawable(mtvP2,Color.rgb(0xff,0x00,0xff));
                mvpGoods.setCurrentItem(1);
                mFlowIndicator.setFocus(1);
                break;
            case R.id.tvP3:
                setDrawable(mtvP3,  Color.rgb(0xff, 0x66, 0xff));
                mvpGoods.setCurrentItem(2);
                mFlowIndicator.setFocus(2);
                break;
            case R.id.tvP4:
                setDrawable(mtvP4,  Color.rgb(0xff, 0x66, 0xff));
                mvpGoods.setCurrentItem(3);
                mFlowIndicator.setFocus(3);
                break;
            case R.id.tvP5:
                setDrawable(mtvP5, Color.rgb(0xff, 0x66, 0xff));
                mvpGoods.setCurrentItem(4);
                mFlowIndicator.setFocus(4);
                break;
        }

    }

    private void initDrawable() {
        setDrawable(mtvP1, Color.BLACK);
        setDrawable(mtvP2,  Color.BLACK);
        setDrawable(mtvP3, Color.BLACK);
        setDrawable(mtvP4, Color.BLACK);
        setDrawable(mtvP5,  Color.BLACK);
    }

    private void setDrawable(TextView textView, int textColor) {
        textView.setTextColor(textColor); // 设置字体颜色

        /*Drawable drawableTop = ContextCompat.getDrawable(this, drawableId);// 根据id获取图片

        // 设置drawableTop的尺寸
        drawableTop.setBounds(0,0,drawableTop.getMinimumWidth(),drawableTop.getMinimumHeight());

        textView.setCompoundDrawables(null, drawableTop, null, null);*/
    }


    class GoodsAdapter extends FragmentStatePagerAdapter {
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
