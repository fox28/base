package com.example.day21_03.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.day21_03.R;

/**
 * Created by apple on 2017/3/12.
 */

public class FlowIndicator extends View {
    int mcount;// 圆的总数
    int mspace;// 圆的间距
    int mradius;// 圆的半径
    int mfocus;  // 焦点圆的索引
    int mNormalColor;   //非焦点圆的索引
    int mFocusColor;    // 焦点圆的索引

    Paint mPaint;

    public int getCount() {
        return mcount;
    }

    public void setCount(int count) {
        this.mcount = count;
        requestLayout();
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
        mcount = typedArr.getInt(R.styleable.FlowIndicator_count,3);
        mspace = typedArr.getDimensionPixelOffset(R.styleable.FlowIndicator_space, 10);
        mradius = typedArr.getDimensionPixelSize(R.styleable.FlowIndicator_radius, 15);
        mfocus = typedArr.getInt(R.styleable.FlowIndicator_focus, 0);
        mNormalColor = typedArr.getColor(R.styleable.FlowIndicator_normal_color, 0xccc);
        mFocusColor = typedArr.getColor(R.styleable.FlowIndicator_focus_color, 0xf00);

        setCount(mcount);
        setFocus(mfocus);

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
            result = getPaddingTop()+getPaddingBottom()+2*mradius;
            height = Math.min(height, result);
        }
        return height;
    }

    private int measureWidth(int widthMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int result=width;
        if (mode != MeasureSpec.EXACTLY) {
            result = getPaddingLeft()+getPaddingRight()+mcount*2*mradius+(mcount-1)*mspace;
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
        mPaint.setStyle(Paint.Style.FILL);
//        int width = getResources().getDisplayMetrics().widthPixels;
//        int paddingLeft = (width - getWidth())/2;
        for (int i = 0; i < mcount; i++) {
            int x = /*paddingLeft +*/ mradius + 2*i*mradius + i*mspace;
            int color =  i==mfocus?mFocusColor:mNormalColor; // 设置焦点圆和非焦点圆的颜色
            mPaint.setColor(color); // 设置画笔的颜色
            canvas.drawCircle(x, mradius,mradius,mPaint); // 参数：x坐标，y坐标，半径，画笔
        }
    }
}
