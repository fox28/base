package com.example.day20_04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button mbtnCategory;

    PopupWindow mPopupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initPopupWindow();

    }

    private void initPopupWindow() {
        // 解析PopupWindow显示的布局
        final View layout = View.inflate(this, R.layout.popup_window, null);
        mPopupWindow = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setAnimationStyle(R.style.style_popup_window);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Toast.makeText(MainActivity.this, "悬浮窗口被关闭", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        mbtnCategory = (Button) findViewById(R.id.btnCategory);
    }

    public void uploadAvatar(View view) {
        final View container = View.inflate(this, R.layout.activity_main, null);
        mPopupWindow.showAtLocation(container, Gravity.BOTTOM, 0, 0);
    }

    public void category(View view) {
        mPopupWindow.showAsDropDown(mbtnCategory);
    }
}
