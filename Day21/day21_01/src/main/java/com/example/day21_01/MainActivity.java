package com.example.day21_01;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager mvpGoods;
    ArrayList<ImageView> mGoodsList;
    GoodsAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initData() {
        ArrayList<Integer> idList = new ArrayList<>();
        idList.add(R.drawable.goods01);
        idList.add(R.drawable.goods02);
        idList.add(R.drawable.goods03);
        idList.add(R.drawable.goods04);
        idList.add(R.drawable.goods05);

        mGoodsList = new ArrayList<>();
        for (int i = 0; i < idList.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(idList.get(i));
            mGoodsList.add(imageView);
        }

    }

    private void initView() {
        mvpGoods = (ViewPager) findViewById(R.id.vpGoods);
        mAdapter = new GoodsAdapter(this, mGoodsList);

        mvpGoods.setAdapter(mAdapter);// 建立与适配器的联系


    }

    class GoodsAdapter extends PagerAdapter {
        Context context;
        ArrayList<ImageView> goodsList;

        public GoodsAdapter(Context context, ArrayList<ImageView> goodsList) {
            this.context = context;
            this.goodsList = goodsList;
        }

        @Override
        public int getCount() {
            return goodsList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView ivGoods = goodsList.get(position);
            container.addView(ivGoods);
            return ivGoods;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
