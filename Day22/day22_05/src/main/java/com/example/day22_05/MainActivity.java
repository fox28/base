package com.example.day22_05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    GestureDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDetector = new GestureDetector(this, new MyGestureListener());

        setListener();

    }

    private void setListener() {
        findViewById(R.id.textView).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDetector.onTouchEvent(event);
                return true;
            }
        });
    }



    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);
        return true;
    }*/

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        // override各个方法


        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.i("main", "轻击");
            return super.onSingleTapUp(e);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i("main", "双击");
            return super.onDoubleTap(e);
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.i("main", "按下未移动");
            super.onShowPress(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            Log.i("main", "按下");
            return super.onDown(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.i("main", "滚动");
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() - e2.getX() > 0 && velocityX > 50) {
                Log.i("main", "向左滑动");
            }
            if(e1.getX() - e2.getX() < 0 && velocityX > 50) {
                Log.i("main", "向右滑动");
            }
           /* if (e2.getY() - e1.getY() > 0 && velocityY > 50) {
                Log.i("main", "向下滑动");
            }
            if (e2.getY() - e1.getY() < 0 && velocityY > 50){
                Log.i("main", "向上滑动");
            }*/
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
}
