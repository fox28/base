package com.example.day21_03.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Scroller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by apple on 2017/3/13.
 */

public class SlideLoopView extends ViewPager {
    GoodsAdapter mAdapter;
    FlowIndicator mFlowIndicator;

    Handler mHandler;
    Timer mTimer;
    int mCount;

    ArrayList<Integer> mGoodsList;

    // 修改1
    boolean misAutoPlay = true;

    public SlideLoopView(Context context) {
        super(context);
    }

    public SlideLoopView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initHandler();
        setListener();

    }

    private void setListener() {
        this.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mFlowIndicator.setFocus(position%mCount);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initHandler() {
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                // 修改2
                if (misAutoPlay) {
                    setCurrentItem(getCurrentItem()+1);
                }

            }
        };
    }




    class GoodsAdapter extends PagerAdapter {
        Context context;
        ArrayList<Integer> goodsList;

        public GoodsAdapter(Context context, ArrayList<Integer> goodsList) {
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
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView ivGoods = new ImageView(context);
            ivGoods.setImageResource(goodsList.get(position%mCount));
            container.addView(ivGoods);

            return ivGoods;
        }
    }


    public void startPlay(Context context, FlowIndicator flowIndicator, ArrayList<Integer> goodsList) {
        mCount = goodsList.size();
        mFlowIndicator = flowIndicator;
        mFlowIndicator.setCount(mCount);
        mFlowIndicator.setFocus(0);

        mAdapter = new GoodsAdapter(context, goodsList);
        this.setAdapter(mAdapter);


        try {
            final Field field = ViewPager.class.getDeclaredField("mScroller");// 反射，拿到ViewPager的私有属性mScroller
            field.setAccessible(true);

            MyScroller scroller = new MyScroller(context);
            scroller.setDuration(2000);

            field.set(this,scroller); // 给field设值   this：当前对象

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0);
            }
        },0,1500);
    }

    public void stopPlay() {
        if (mTimer == null) {
            return;
        }
        mTimer.cancel();
    }


    class MyScroller extends Scroller {
        private int duration;

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public MyScroller(Context context) {
            super(context);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, this.duration);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                misAutoPlay = false;
                break;
            default:
                misAutoPlay = true;
        }
        return super.onTouchEvent(ev);
    }
}
