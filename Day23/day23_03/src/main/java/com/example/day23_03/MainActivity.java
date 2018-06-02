package com.example.day23_03;

import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    VideoView mVideoView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mVideoView = (VideoView) findViewById(R.id.videoView);
    }

    public void onPlayFromNetwork(View view) {
        mVideoView.setVideoURI(Uri.parse("http://player.video.qiyi.com/f998dc73b4e7d02461b7ec17fb299e1d/0/0/v_19rrb7z07o.swf-albumId=637839800-tvId=637839800-isPurchase=0-cnId=31"));
        MediaController controller = new MediaController(this);
        mVideoView.setMediaController(controller); // 设置播放器控制模式
        mVideoView.start();


    }

    public void onPlayFromSDCard(View view) {
        /*File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        File file = new File(dir, "shengshengshiai.mkv");
        mVideoView.setVideoURI(Uri.fromFile(file));
        MediaController controller = new MediaController(this);
        mVideoView.setMediaController(controller);
        mVideoView.start();
*/
        Cursor c = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        if (c.moveToNext()) {
            String path = c.getString(c.getColumnIndex(MediaStore.Audio.Media.DATA));

            mVideoView.setVideoPath(path);
            MediaController controller = new MediaController(this);
            mVideoView.setMediaController(controller);
            mVideoView.start();

        }



    }

    public void onStopPlay(View view) {
        mVideoView.stopPlayback();


    }
}
