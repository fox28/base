package com.example.day16_02;

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
    CartReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        myReceiver = new CartReceiver();
        IntentFilter filter = new IntentFilter("update_cart_hint");
        registerReceiver(myReceiver, filter);

    }


    /**
     * 购物车实例化
     */
    private void initView() {
        mtvCartHint = (TextView) findViewById(R.id.tvCartHint);
    }


    public void onClick(View view) {
        String str = mtvCartHint.getText().toString();
        int count = Integer.parseInt(str);

        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("count", count);
        startActivity(intent);
    }

    class CartReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int count = intent.getIntExtra("count",0);
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
