package com.example.ex23_04;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static final int ACTION_CAPTURE = 0;
    private static final int ACTION_CHOOSE = 1;
    private static final int ACTION_CROP = 2;

    ImageView mivAvatar;
    File mFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mivAvatar = (ImageView) findViewById(R.id.ivAvatar);
    }

    /**
     * 拍照，打开照相机
     * @param view
     */
    public void capture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        mFile = new File(dir, System.currentTimeMillis() + ".jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mFile));

        startActivityForResult(intent, ACTION_CAPTURE);

    }

    public void choose(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");

        startActivityForResult(intent, ACTION_CHOOSE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ACTION_CAPTURE:
                    startCrop(Uri.fromFile(mFile), 200, 200);
                    break;
                case ACTION_CHOOSE:
                    startCrop(data.getData(), 200, 200);
                    break;
                case ACTION_CROP:
                    showAvatar(data);
            }
        }
    }

    private void showAvatar(Intent data) {
        Bitmap bitmap = data.getParcelableExtra("data");
        mivAvatar.setImageBitmap(bitmap);
    }

    private void startCrop(Uri uri, int outputX, int outputY) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);

        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        startActivityForResult(intent, ACTION_CROP);
    }
}
