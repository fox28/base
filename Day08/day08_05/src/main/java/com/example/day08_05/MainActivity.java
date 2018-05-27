package com.example.day08_05;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ProgressBar mBar;
    TextView mvtProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mBar = (ProgressBar) findViewById(R.id.bar);
        mvtProgress = (TextView) findViewById(R.id.tvProgress);
    }

    public void onClick(View view) {
        new MyAsyncTask().execute("http://10.0.2.2/aap.json");
    }

    class MyAsyncTask extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected void onPreExecute() {
            Toast.makeText(MainActivity.this, "开始下载", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            for (int i=1; i<=100; i++) {
                SystemClock.sleep(30);
                publishProgress(i,100);
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progress = values[0];
            int max = values[1];
            mBar.setMax(max);
            mBar.setProgress(progress);
            mvtProgress.setText(progress+"%");
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Toast.makeText(MainActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
            } 
        }
    }

}
