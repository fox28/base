package com.example.day22_02;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by apple on 2017/3/15.
 */

public class MyTextView extends AppCompatTextView {


    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("main", "MyTextView.event. action:" + event.getAction());

        return true;
    }
}
