package com.example.day21_e1.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.day21_e1.R;

/**
 * Created by apple on 2017/3/14.
 */

public class FlowIndicator extends View {
    int mCount;
    int mSpace;
    int mLength;
    int mFocus;
    int mNormalColor;
    int mFocusColor;

    Paint mPaint;

    public int getmCount() {
        return mCount;
    }

    public void setCount(int count) {
        this.mCount = count;
        invalidate();
    }

    public int getmFocus() {
        return mFocus;
    }

    public void setFocus(int focus) {
        this.mFocus = focus;
        invalidate();
    }

    public FlowIndicator(Context context) {
        super(context);
    }

    public FlowIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        final TypedArray typedArr = context.obtainStyledAttributes(attrs, R.styleable.FlowIndicator);
        mCount = typedArr.getInt(R.styleable.FlowIndicator_count, 3);
        mSpace = typedArr.getDimensionPixelOffset(R.styleable.FlowIndicator_space,3);
        mLength = typedArr.getDimensionPixelOffset(R.styleable.FlowIndicator_length,15);
        mFocus = typedArr.getInt(R.styleable.FlowIndicator_focus, 0);
        mNormalColor = typedArr.getColor(R.styleable.FlowIndicator_normal_color, 0xccc);
        mFocusColor = typedArr.getColor(R.styleable.FlowIndicator_focus_color, 0xf00);

        mPaint = new Paint();

        /*
        setCount(mcount);
        setFocus(mfocus);
         */

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
            result = getPaddingTop()+getPaddingBottom()+5;
            height = Math.min(height, result);
        }
        return height;
    }

    private int measureWidth(int widthMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int result=width;
        if (mode != MeasureSpec.EXACTLY) {
            result = getPaddingLeft()+getPaddingRight()+ mCount*mLength +(mCount-1)*mSpace;
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

        mPaint.setStrokeWidth(32);

        for (int i = 0; i < mCount; i++) {

            int x = i*mLength + i*mSpace;
            int color=i == mFocus? mFocusColor:mNormalColor; //设置焦点线和非焦点线的颜色

            mPaint.setColor(color);

            canvas.drawLine(x,0,x+mLength,0,mPaint);
        }
    }
}
