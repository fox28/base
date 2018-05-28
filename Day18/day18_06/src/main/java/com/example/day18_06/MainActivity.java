package com.example.day18_06;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {
    static final String FILE_NAME = "lama.png";

    ImageView mivLama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        mivLama = (ImageView) findViewById(R.id.ivLama);
    }

    public void on215x200(View view) {
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(dir, FILE_NAME);
        try {
            FileInputStream fis = new FileInputStream(file);
            BitmapFactory.Options options = new BitmapFactory.Options();

            // 获取图片的尺寸，只
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(fis, null, options);

            // 计算xy方向的比例，并获取最大压缩比
            int scaleX= options.outWidth/215;
            int scaleY = options.outHeight/200;
            if (scaleX > scaleY) {
                options.inSampleSize = scaleX;
            } else {
                options.inSampleSize = scaleY;
            }

            options.inJustDecodeBounds = false;
            fis = new FileInputStream(file);
            Bitmap bitmap = BitmapFactory.decodeStream(fis, null, options);
            mivLama.setImageBitmap(bitmap);
            Log.i("main", bitmap.getByteCount() / (1 << 10) + "kb");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void onQuality(View view) {
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(dir, FILE_NAME);
        try {
            FileInputStream fis = new FileInputStream(file);
            BitmapFactory.Options options = new BitmapFactory.Options();

            // 只获取图片的尺寸
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(fis, null, options);

            // 计算x方向的比例
            int scaleX = options.outWidth / 215;
            int scaleY = options.outHeight / 200;

            // 获取横向、纵向的最大压缩比
            if (scaleX > scaleY) {
                options.inSampleSize = scaleX;
            } else {
                options.inSampleSize = scaleY;
            }

            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            fis = new FileInputStream(file);
            Bitmap bitmap = BitmapFactory.decodeStream(fis,null,options);
            mivLama.setImageBitmap(bitmap);
            Log.i("main", bitmap.getByteCount() / (1 << 10) + "kb");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void on430x400(View view) {
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(dir, FILE_NAME);
        try {
            FileInputStream fis = new FileInputStream(file);
            Bitmap bitmap = BitmapFactory.decodeStream(fis);
            mivLama.setImageBitmap(bitmap);
            Log.i("main", bitmap.getByteCount() / (1 << 10) + "KB");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
