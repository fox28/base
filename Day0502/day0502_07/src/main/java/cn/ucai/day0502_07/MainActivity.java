package cn.ucai.day0502_07;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ProgressBar mprogressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mprogressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    public void onClick(View view) {
        new Thread() {
            @Override
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    mprogressBar.setProgress(i);
                    SystemClock.sleep(30);
                }
                mprogressBar.setProgress(80);
                SystemClock.sleep(300);
                mprogressBar.setProgress(60);
                SystemClock.sleep(300);
                mprogressBar.setProgress(40);
                SystemClock.sleep(300);
                mprogressBar.setProgress(20);
            }

        }.start();
    }
}
