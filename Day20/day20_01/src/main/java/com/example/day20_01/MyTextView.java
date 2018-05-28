package com.example.day20_01;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by apple on 2017/3/11.
 */

public class MyTextView extends View {
    private String mText;
    private int mTextColor;
    private int mTextSize;

    Paint mpaint;
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 从布局文件中获取自定义属性数组——TypedArray
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        mText = typedArray.getString(R.styleable.MyTextView_text01);
        mTextColor = typedArray.getColor(R.styleable.MyTextView_textColor, 0xfff);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.MyTextView_textSize, 16);

        mpaint = new Paint();
        mpaint.setTextSize(mTextSize);// !!! 务必保存当前文字的宽度
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
    }

    /**
     * 测量MyTextView的高度
     * @param heightMeasureSpec 系统传入的测量高度信息（包括：系统测量的高度、测量规格）
     * @return
     */
    private int measureHeight(int heightMeasureSpec) {
        // 获取系统测量的高度，当布局layout_height的属性值为固定值或者match_parent时，height的值就是精确值
        int height = MeasureSpec.getSize(heightMeasureSpec);

        //获取系统测量规格
        int mode = MeasureSpec.getMode(heightMeasureSpec);
//        MeasureSpec.EXACTLY; 精确值
//        MeasureSpec.AT_MOST;  不精确值

        int size ;
        if (mode == MeasureSpec.AT_MOST) {
            size = (int) (getPaddingTop()+getPaddingBottom() +mpaint.descent() - mpaint.ascent());
            Log.i("main", "getPaddingTop:" + getPaddingTop() + ",getPaddingBottom:" + getPaddingBottom());
            height = Math.min(height, size);
        }
        return height;
    }


    /**
     * 测量TextView的宽度
     * @param widthMeasureSpec 系统传入的测量宽度的信息
     * @return
     */
    private int measureWidth(int widthMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);

        if (mode == MeasureSpec.AT_MOST) {
            int size = (int) (getPaddingLeft() + getPaddingRight() + mpaint.measureText(mText));
            Log.i("main", "getPaddingLeft:" + getPaddingLeft() + ",getPaddingRight:" + getPaddingRight());
            width = Math.min(width, size);
        }
        return width;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mpaint.setColor(mTextColor);
        mpaint.setTextSize(mTextSize);
        canvas.drawText(mText, 10, -mpaint.ascent(), mpaint);
    }


}
