package com.example.day23_02;

import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayer = new MediaPlayer();
        setListener();


    }

    /*private void setListener() {
        SetOnCompleteListener();
        SetOnErrorListener();
    }

    private void SetOnErrorListener() {
        mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(MainActivity.this, "播放错误",Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    private void SetOnCompleteListener() {
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(MainActivity.this, "播放完毕", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void playFromNetwork(View view) {

        try {
            mPlayer.reset();
            mPlayer.setDataSource(this, Uri.parse("http://10.0.2.2/yielaixiang.mp3"));
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void playFromSDCard(View view) {
        Cursor cursor= getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
//        File path2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
//        File file = new File(path2,"Help.mp3");


        if (cursor.moveToNext()) {
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            try {
                mPlayer.reset();
//                mPlayer.setDataSource(this, Uri.fromFile(file));
                mPlayer.setDataSource(this,Uri.fromFile(new File(path)));
                mPlayer.prepare();
                mPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    public void stopPlay(View view) {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.stop();
        }

    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mPlayer.release();
    }*/




    private void setListener() {
        setOnCompleteListetner();
        setOnErrorListener();
    }

    private void setOnErrorListener() {
        mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(MainActivity.this, "播放错误", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void setOnCompleteListetner() {
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(MainActivity.this, "播放完毕", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void playFromNetwork(View view) {
        try {
            mPlayer.reset();
            mPlayer.setDataSource(this, Uri.parse("http://10.0.2.2/lianqu1990.mp3"));
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playFromSDCard(View view) {
        Cursor c = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        if (c.moveToNext()) {
            String path = c.getString(c.getColumnIndex(MediaStore.Audio.Media.DATA));
            try {
                mPlayer.reset();
                mPlayer.setDataSource(this,Uri.fromFile(new File(path)));
                mPlayer.prepare();
                mPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopPlay(View view) {
        if (mPlayer!=null && mPlayer.isPlaying()) {
            mPlayer.stop();
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mPlayer.release();
    }





}
