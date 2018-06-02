package com.example.day21_e02.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.day21_e02.R;


/**
 * Created by apple on 2017/7/6.
 */

public class FlowIndicator extends View {
    private int mCount;
    private int mFocus;
    private int mSeparation;
    private int mRadius;
    private int mNormalColor;
    private int mFocusColor;

    private Paint mPaint;

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
    }

    public int getFocus() {
        return mFocus;
    }

    public void setFocus(int focus) {
        mFocus = focus;
        invalidate();//刷新界面
    }

    public FlowIndicator(Context context) {
        super(context);
    }

    public FlowIndicator(Context context,  AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.FlowIndicator);
        mCount = array.getInt(R.styleable.FlowIndicator_count, 2);
        mFocus = array.getInt(R.styleable.FlowIndicator_focus, 1);
        mSeparation = array.getDimensionPixelOffset(R.styleable.FlowIndicator_separation, 10);
        mRadius = array.getDimensionPixelSize(R.styleable.FlowIndicator_radius,15);
        mNormalColor = array.getColor(R.styleable.FlowIndicator_normalColor, 0xccc);
        mFocusColor = array.getColor(R.styleable.FlowIndicator_focusColor, 0xf00);


        setCount(mCount);
        setFocus(mFocus);

        mPaint = new Paint();
        mPaint.setAntiAlias(true); // 抗锯齿、防抖动的效果
        /**
         *  array.recycle() 回收TypeArray对象，用于后续复用
         * FlowIndicator随着Activity的每次创建而创建，如果不recycle(),频繁创建array对内存和性能是一个不小的开销
         * 如果不使用池模式，每次都让GC回收，可能造成OOM
         */
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(widthMeasureSpec), getMeasuredHeight(heightMeasureSpec));

    }

    private int getMeasuredHeight(int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int result =height;
        if(mode!=MeasureSpec.EXACTLY){
            result = getPaddingTop()+getPaddingBottom() + 2*mRadius;
            result = Math.min(result, height);
        }
        return result;
    }

    private int getMeasuredWidth(int widthMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

        int result = width;
        if (mode != MeasureSpec.EXACTLY) {
            result = getPaddingLeft()+getPaddingRight()+2*mRadius*mCount+ (mCount-1)*mSeparation;
            result = Math.min(result,width);
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < mCount; i++) {
            int x = mRadius + 2*mRadius*i + i*mSeparation;
            mPaint.setColor(i==mFocus?mFocusColor:mNormalColor);
            canvas.drawCircle(x,mRadius,mRadius,mPaint);
        }
    }
}
