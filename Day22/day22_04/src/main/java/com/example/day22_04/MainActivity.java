package com.example.day22_04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListener();
    }

    String[] mActions = {"down", "up", "move"};

    private void setListener() {
        findViewById(R.id.view).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getActionMasked();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < event.getPointerCount(); i++) {
                    action=action>=5?action-5:action;
                    sb.append("手指").append(event.getPointerId(i)+1)
                            .append(", action").append(mActions[action]);
                    Log.i("main", sb.toString());
                }



                return true;
            }
        });

    }





}
