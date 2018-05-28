package com.example.day16_02_02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView mtvCartHint;
    MyReceiver myReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        注册广播接受者
         */
        initView();
        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter("update_cart_hint");
        registerReceiver(myReceiver, filter);

    }

    public void onClick(View view) {
        String str = mtvCartHint.getText().toString();
        int count = Integer.parseInt(str);

        final Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("count", count);
        startActivity(intent);
    }

    private void initView() {
        mtvCartHint = (TextView) findViewById(R.id.tvCartHint);
    }

    /**
     * 定义广播者类
     */
    class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int count = intent.getIntExtra("count", 0);
            if (count >= 0) {
                mtvCartHint.setText(count+"");
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
}
