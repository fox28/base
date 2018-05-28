package com.example.day18_04;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button mbtnAddView, mbtnRemoveView;
    LinearLayout mLayout;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLayout = (LinearLayout) View.inflate(this, R.layout.activity_main, null);
        setContentView(mLayout);

        initView();
    }

    private void initView() {
        mbtnAddView = (Button) findViewById(R.id.btnAddView);
        mbtnRemoveView = (Button) findViewById(R.id.btnRemoveView);
        mbtnRemoveView.setEnabled(false);
    }

    public void addView(View view) {
        if (mbtnAddView.isEnabled()) {
            cteateTextView();
            mbtnAddView.setEnabled(false);
            mbtnRemoveView.setEnabled(true);
        }

    }

    /**
     * 方法创建TextView
     */
    private void cteateTextView() {
        tv = new TextView(this);
        tv.setLayoutParams(new LinearLayout.LayoutParams(dp2px(200), ViewGroup.LayoutParams.WRAP_CONTENT));
        tv.setBackgroundColor(Color.YELLOW);
        tv.setTextColor(Color.RED);
        tv.setGravity(Gravity.CENTER_HORIZONTAL);
        tv.setText("俺是被自定义new出来的");
        mLayout.addView(tv);

    }

    private int dp2px(int i) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (density*i);
    }

    public void removeView(View view) {
        if (mbtnRemoveView.isEnabled()) {
            mLayout.removeView(tv);
            mbtnAddView.setEnabled(true);
            mbtnRemoveView.setEnabled(false);

        }
    }

}
