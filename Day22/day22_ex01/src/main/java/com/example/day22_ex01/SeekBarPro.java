package com.example.day22_ex01;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.SeekBar;

/**
 * Created by apple on 2017/3/19.
 */

public class SeekBarPro extends SeekBar {
    boolean isDrop;
    public SeekBarPro(Context context) {
        super(context);
    }

    public SeekBarPro(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MySeekBar);
        isDrop = array.getBoolean(R.styleable.MySeekBar_enableDrop, true);
        Log.i("main", "isDrop:" + isDrop);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isDrop) {
            // 调用SeekBar.onTouchEvent()
            // SeekBar本身支持拖拽功能
            return super.onTouchEvent(event);
        }
        return isDrop;
    }
}
