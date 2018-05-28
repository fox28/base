package com.example.day20_02;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by apple on 2017/3/12.
 */

public class FlowIndicator extends View {
    int mCount;// 圆的总数
    int mSpace;// 圆的间距
    int mRadius;// 圆的半径
    int mfocus;  // 焦点圆的索引
    int mNormalColor;   //非焦点圆的颜色
    int mFocusColor;    // 焦点圆的颜色

    Paint mPaint;

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        this.mCount = count;
    }

    public int getFocus() {
        return mfocus;
    }

    public void setFocus(int focus) {
        this.mfocus = focus;
        invalidate();
    }

    public FlowIndicator(Context context) {
        super(context);
    }

    public FlowIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        final TypedArray typedArr = context.obtainStyledAttributes(attrs, R.styleable.FlowIndicator);
        mCount = typedArr.getInt(R.styleable.FlowIndicator_count,3);
        mSpace = typedArr.getDimensionPixelOffset(R.styleable.FlowIndicator_space, 10);
        mRadius = typedArr.getDimensionPixelSize(R.styleable.FlowIndicator_radius, 15);
        mfocus = typedArr.getInt(R.styleable.FlowIndicator_focus, 0);
        mNormalColor = typedArr.getColor(R.styleable.FlowIndicator_normal_color, 0xccc);
        mFocusColor = typedArr.getColor(R.styleable.FlowIndicator_focus_color, 0xf00);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        typedArr.recycle();//回收
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
    }

    private int measureHeight(int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int result;
        if (mode != MeasureSpec.EXACTLY) {
            result = getPaddingTop()+getPaddingBottom()+2* mRadius;
            height = Math.min(height, result);
        }
        return height;
    }

    private int measureWidth(int widthMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int result=width;
        if (mode != MeasureSpec.EXACTLY) {
            result = getPaddingLeft()+getPaddingRight()+ mCount *2* mRadius +(mCount -1)* mSpace;
            result = Math.min(width, result);
        }
        return result;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        int width = getResources().getDisplayMetrics().widthPixels;
//        int paddingLeft = (width - getWidth())/2;
        for (int i = 0; i < mCount; i++) {
            int x = /*paddingLeft +*/ mRadius + 2*i* mRadius + i* mSpace;
            int color =  i==mfocus?mFocusColor:mNormalColor; // 设置焦点圆和非焦点圆的颜色
            mPaint.setColor(color); // 设置画笔的颜色
            canvas.drawCircle(x, mRadius, mRadius,mPaint);
        }
    }
}
