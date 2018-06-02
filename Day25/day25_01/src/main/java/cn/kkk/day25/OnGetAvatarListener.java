package cn.kkk.day25;

/**
 * Created by apple on 2017/3/21.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import java.io.File;

/**
 * 启动系统拍照的Activity
 * 启动系统相册选取的Activity
 * 启动系统剪裁的Activity
 * 显示头像
 * 处理以上三个Activity的返回结果
 */
public class OnGetAvatarListener {
    private static final int ACTION_CAPTURE = 0;
    private static final int ACTION_CHOOSE = 1;
    private static final int ACTION_CROP = 2;

    PopupWindow mPopupWindow;
    Activity mContext;
    File mFile;

    public OnGetAvatarListener(Activity context, int containerId) {
        this.mContext = context;

        // 获取父类的View
        View parent = View.inflate(context, containerId, null); // 解析父容器
        View layout = View.inflate(context, R.layout.popup_window, null);

        initPopupWindow(parent, layout);

        setListener(layout);

    }

    private void setListener(View layout) {
        setCaptureListener(layout);
        setChooseListener(layout);
    }

    private void setChooseListener(View layout) {
        layout.findViewById(R.id.tvChoose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, "image/*");
                mContext.startActivityForResult(intent,ACTION_CHOOSE);
            }
        });

    }

    private void setCaptureListener(View layout) {
        layout.findViewById(R.id.tvCapture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                mFile = new File(dir, System.currentTimeMillis() + ".jpg");
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mFile));
                mContext.startActivityForResult(intent,ACTION_CAPTURE);
            }
        });
    }


    private void initPopupWindow(View parent, View layout) {
        mPopupWindow = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setAnimationStyle(R.style.popup_window_style);
        mPopupWindow.showAtLocation(parent, Gravity.BOTTOM,0,0);
    }


    public void setAvatar(Intent intent, int requestCode, ImageView ivAvatar) {
        switch (requestCode) {
            case ACTION_CAPTURE:
                startCrop(Uri.fromFile(mFile), 200, 200);
                break;
            case ACTION_CHOOSE:
                startCrop(Uri.fromFile(mFile),200,200);
                break;
            case ACTION_CROP:
                showAvatar(intent, ivAvatar);
                break;

        }
    }

    private void showAvatar(Intent intent, ImageView ivAvatar) {
        Bitmap bitmap = intent.getParcelableExtra("data");
        if (bitmap == null) {
            return;
        }
        ivAvatar.setImageBitmap(bitmap);

    }

    private void startCrop(Uri uri, int outputX, int outputY) {
        Intent intent = new Intent("com.android.camera.action.Crop");
        intent.setDataAndType(uri, "image/*");

        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);

        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        mContext.startActivityForResult(intent, ACTION_CROP);

    }

}
