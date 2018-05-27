package com.example.day07_03;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    public void getPicturesInfo(View view) {
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            StringBuilder sb = new StringBuilder();
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media._ID));
            sb.append("id:").append(id);
            String displayName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
            sb.append(", displayName: ").append(displayName);
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            sb.append(", path:").append(path);
            int size = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media.SIZE));
            sb.append(", size: ").append(size);
            int width = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media.WIDTH));
            sb.append(", width: ").append(width);
            int height = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media.HEIGHT));
            sb.append(", height: ").append(height);

            Log.i("main", sb.toString());
            Toast.makeText(this, "已经写到日志窗口", Toast.LENGTH_SHORT).show();
        }
    }


}
