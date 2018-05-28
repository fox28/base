package com.example.day20_02;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    int mCount;
    int mFocus;

    FlowIndicator mFlowIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mFlowIndicator = (FlowIndicator) findViewById(R.id.flowIndicator);

    }


    class MyAsyncTask extends AsyncTask<Void,Integer,Void> {

        @Override
        protected Void doInBackground(Void... params) {
            while (true) {
                mFocus=(mFocus+1)%mCount;
                publishProgress(mFocus);
                SystemClock.sleep(1000);
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mFlowIndicator.setFocus(values[0]);
        }
    }


    public void onClick(View view) {
        mCount = mFlowIndicator.getCount();
        mFocus = mFlowIndicator.getFocus();

        new MyAsyncTask().execute();

    }


}
