package com.example.day21_e02.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
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
 * Created by apple on 2017/7/7.
 */

public class SlideLoopView extends ViewPager {
    FlowIndicator mIndicator;
    GoodsAdapter mAdapter;

    Handler mHandler;
    Timer mTimer;
    int mCount;

    // 自动播放
    boolean mIsAutoPlay = true;

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
                mIndicator.setFocus(position%mCount);
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
                super.handleMessage(msg);
                if (mIsAutoPlay) {
                    setCurrentItem(getCurrentItem()+1);
                }
            }
        };
    }

    class GoodsAdapter extends PagerAdapter{

        Context context;
        ArrayList<Integer> arrayList;

        public GoodsAdapter(Context context, ArrayList<Integer> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
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
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = new ImageView(context);
            iv.setImageResource(arrayList.get(position%mCount));
            container.addView(iv);
            return iv;
        }
    }

    public void startPlay(Context context, FlowIndicator flowIndicator, ArrayList<Integer> arrayList ){
        mCount = arrayList.size();
        mIndicator = flowIndicator;
        mIndicator.setFocus(0);
        mIndicator.setCount(mCount);

        mAdapter = new GoodsAdapter(context, arrayList);
        this.setAdapter(mAdapter);

        try {
            Field field = ViewPager.class.getDeclaredField("mScroller"); // 反射，拿到ViewPager的私有属性mScroller
            field.setAccessible(true);

            MyScroller scroller = new MyScroller(context);
            scroller.setDuration(2000);

            field.set(this, scroller);

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
        },0, 1500);
    }

    public void stopPlay(){
        if (mTimer == null) {
            return;
        }
        mTimer.cancel();
    }
//
    class MyScroller extends Scroller{
        private int duration;

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public MyScroller(Context context) {
            super(context);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, duration);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                mIsAutoPlay = false;
                break;
            default:
                mIsAutoPlay =true;
                break;

        }
        return super.onTouchEvent(ev);
    }
}

