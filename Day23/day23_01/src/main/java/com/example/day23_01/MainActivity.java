package com.example.day23_01;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import static com.example.day23_01.R.id.tvState;

public class MainActivity extends AppCompatActivity {
    TextView mtvState;
    MediaPlayer mPlayer;
    boolean misPause;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        misPause = false;
        initView();
    }

    private void initView() {
        mtvState = (TextView) findViewById(tvState);
    }

    public void play(View view) {
        if (mPlayer == null) {
            // 调用MediaPlayer对象的create方法读取指定资源索引的文件
            mPlayer = MediaPlayer.create(this, R.raw.lianqu1990);
            mPlayer.start();
            mtvState.setText("\"lianqu1990\"播放中……");
        }
        if (mPlayer.isPlaying()) {// 若正在播放
            return;
        }

        /**
         * stop和pause状态下
         */
        if (misPause) { // pause状态下
            mPlayer.start();
            misPause = false;
            mtvState.setText("\"lianqu1990\"播放中……");
        } else {// stop状态下
            try {
                mPlayer.prepare();
                mPlayer.start();
                mtvState.setText("\"lianqu1990\"播放中……");
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    public void stop(View view) {
        if (mPlayer.isPlaying()) {
            mPlayer.stop();
            mtvState.setText("播放停止");
        }


    }

    public void pause(View view) {
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
            mtvState.setText("播放暂停");
            misPause = true;
        }

    }
}
