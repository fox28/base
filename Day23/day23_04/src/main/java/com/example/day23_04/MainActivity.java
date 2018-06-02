package com.example.day23_04;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    SurfaceView mSurfaceView;
    MediaPlayer mMediaPlayer;
    int mPosition; // 保存播放的断点



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListener();
    }







    private void setListener() {
        mSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (mPosition > 0) {
                    startPlay();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                mPosition = mMediaPlayer.getCurrentPosition();
                mMediaPlayer.release();
                mMediaPlayer = null;

            }
        });
    }

    private void initView() {
        mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);
    }

    public void onPlayVideo(View view) {
        startPlay();
    }

    private void startPlay() {
        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
        }
        mMediaPlayer.reset();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setDisplay(mSurfaceView.getHolder());


        try {
            File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
            File file = new File(dir, "shengshengshiai.mkv");
            mMediaPlayer.setDataSource(this, Uri.fromFile(file));
//            mPlayer.setDataSource(this, Uri.parse("http://10.0.2.2/shengshengshiai.mkv"));
            mMediaPlayer.prepare();
            mMediaPlayer.seekTo(mPosition);
            mMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
