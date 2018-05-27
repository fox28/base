package cn.ucai.day05_01;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ProgressBar mProgressBar;
    SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        interview();
        setListener();


    }

    private void setListener() {
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("main", "progress = " +progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "开始拖动Seeker", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Seek拖动结束", Toast.LENGTH_SHORT).show();
            }

        });
    }

    /**
     * mProgressBar初始化
     */
    private void interview() {
        mProgressBar = (ProgressBar) findViewById(R.id.bar);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);

    }


    /**
     * ProgressBar按钮
     *
     * @param view
     */
    public void onClick(View view) {
        new Thread(){
            @Override
            public void run() {
                for(int i=0; i<100; i++) {
                    mProgressBar.setProgress(i);
                    SystemClock.sleep(30);
                }
                mProgressBar.setProgress(0);
            }
        }.start();
    }

}