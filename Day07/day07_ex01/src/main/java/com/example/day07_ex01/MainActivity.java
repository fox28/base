package com.example.day07_ex01;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView mivImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mivImage = (ImageView) findViewById(R.id.ivImage);

    }

    /**
     * 查询SD卡的所有图片文件信息
     * 在对话框中显示图片文件名列表
     * 点击其中一项显示该项的图片
     * @param view
     */
    public void getPicturesInfo(View view) {
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        final ArrayList<Image> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String display = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            list.add(new Image(display, path));
        }
        String[] titleArr = new String[list.size()];
        for (int i=0; i<list.size(); i++) {
            titleArr[i] = list.get(i).getTitle();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择图片")
                .setItems(titleArr, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bitmap bitmap = BitmapFactory.decodeFile(list.get(which).getPath());
                        mivImage.setImageBitmap(bitmap);
                    }
                }).create().show();

    }
}
