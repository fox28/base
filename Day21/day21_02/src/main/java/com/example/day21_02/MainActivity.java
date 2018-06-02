package com.example.day21_02;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.day21_02.utils.OkImageLoader;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    static final String ROOT_URL = "http://10.0.2.2/";
    ViewPager mvpGoods;
    ArrayList<String> mGoodsList;
    GoodsAdapter mAdapter;

    Handler mHandler;
    Timer mTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();

        initHandler();
    }

    private void initHandler() {
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                mvpGoods.setCurrentItem(mvpGoods.getCurrentItem()+1);
            }
        };
    }

    private void initView() {
        mvpGoods = (ViewPager) findViewById(R.id.vpGoods);
        mAdapter = new GoodsAdapter(this, mGoodsList);
        mvpGoods.setAdapter(mAdapter);

    }

    private void initData() {
        mGoodsList = new ArrayList<>();
        mGoodsList.add("goods01.png");
        mGoodsList.add("goods02.png");
        mGoodsList.add("goods03.png");
        mGoodsList.add("goods04.png");
        mGoodsList.add("goods05.png");
    }

    class GoodsAdapter extends PagerAdapter {
        Context context;
        ArrayList<String> goodsList;

        public GoodsAdapter(Context context, ArrayList<String> goodsList) {
            this.context = context;
            this.goodsList = goodsList;
        }


        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView ivGoods = new ImageView(context);
            container.addView(ivGoods);
            OkImageLoader.build(ROOT_URL+goodsList.get(position%goodsList.size()))
                    .defaultPicture(R.drawable.goods01)
                    .width(160)
                    .height(400)
                    .imageView(ivGoods)
                    .showImage(context);
            return ivGoods;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    public void startPlay(View view) {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0);
            }
        },0,1500);
    }

    public void stopPlay(View view) {
        if (mTimer == null) {
            return;
        }
        mTimer.cancel();
    }

}
