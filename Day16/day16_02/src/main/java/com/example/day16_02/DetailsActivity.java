package com.example.day16_02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DetailsActivity extends AppCompatActivity {
    int mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        mCount = intent.getIntExtra("count", 0);
    }


    public void onAddGoods(View view) {
        mCount++;
        Intent intent = new Intent("update_cart_hint");
        intent.putExtra("count", mCount);
        sendBroadcast(intent);
    }

    public void onDeleteGoods(View view) {
        if (mCount>0) {
            mCount--;
        }
        Intent intent = new Intent("update_cart_hint");
        intent.putExtra("count", mCount);
        sendBroadcast(intent);

    }
}
