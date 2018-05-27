package com.example.day07_ex02;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onPlayMusic(View view) {
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        final ArrayList<Music> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            list.add(new Music(title, path));
        }

        String[] titleArr = new String[list.size()];
        for (int i=0; i<list.size(); i++) {
            titleArr[i] = list.get(i).getTitle();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择歌曲")
                .setItems(titleArr, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 创建播放音乐的媒体播放器对象
                        MediaPlayer player = new MediaPlayer();
                        try {
                            player.setDataSource(list.get(which).getPath());// 设置播放的文件路径
                            player.prepare();//进入准备状态
                            player.start();// 开始播放
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).create().show();
    }

}
