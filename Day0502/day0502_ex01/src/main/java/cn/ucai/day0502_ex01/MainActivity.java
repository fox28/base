package cn.ucai.day0502_ex01;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SeekBar mSeekBar;

    TextView mtvSalary;
    SeekBar mSeekBarSalary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListener();

    }

    private void setListener() {
        mSeekBarSalary.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int salary = progress;
                mtvSalary.setText("期望薪资："+salary+"K");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    private void initView() {
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mtvSalary = (TextView) findViewById(R.id.tvSalary);
        mSeekBarSalary = (SeekBar) findViewById(R.id.seekBarSalary);
    }


    public void onClick(View view) {

        new Thread(){
            @Override
            public void run() {
                for (int i=1; i<=100; i++) {
                    mSeekBar.setProgress(i);
                    SystemClock.sleep(30);
                }


            }
        }.start();
    }



}
