package com.example.day18_02;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by apple on 2017/3/9.
 */

public class ScreenView extends View {
    private int mWallPageId = R.drawable.bg01;
    public ScreenView(Context context) {
        super(context);
    }

    public ScreenView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setWallPageId(int wallPageId) {
        this.mWallPageId = wallPageId;
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.GRAY);// 灰色背景
        Paint paint = new Paint();  // 实例化画笔
        paint.setColor(Color.WHITE);// 画笔颜色
        paint.setStyle(Paint.Style.FILL); //设置填充方式、实心

        int width = getWidth() ;//计算显示器的宽度
        int height = width * 9 / 16; // 设置显示器的高度

        /*
        矩形1：底部背景
        背景颜色灰色
         */
        RectF rect1 = new RectF(10, 10, width - 10, height + 10);
        canvas.drawRoundRect(rect1, 20, 20, paint);

        // 内部矩形，画笔颜色、灰色
        /*
        矩形2：显示器内容
         */
        RectF rect2 = new RectF(30, 50, width - 30, height - 40);
        paint.setColor(Color.GRAY);
        canvas.drawRect(rect2, paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        canvas.drawText("PHILIPS 170W4", 30, 40, paint);

        /*
        加载图片1： 显示器中的图片
         */
        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, mWallPageId); // 将资源图片转换为位图对象
        Rect rect3 = new Rect(35, 55,width-35, height-45);
        canvas.drawBitmap(bitmap, rect3, rect3, paint);

        /*
        图片2：图片1下面
         */
        bitmap = BitmapFactory.decodeResource(res, R.drawable.philips);
        canvas.drawBitmap(bitmap,width/2-bitmap.getWidth()/2, height-40,paint);

       /* Resources res2 = getResources();
        Bitmap bitmap2 = BitmapFactory.decodeResource(res2, R.drawable.bg02);
        rect3 = new Rect(width / 2 - 43, height - 40, width / 2 + 43, height - 20);
        canvas.drawBitmap(bitmap2,rect3,rect3,paint);*/



        /*
        图片3：底座
         */
        bitmap = BitmapFactory.decodeResource(res, R.drawable.base);
        canvas.drawBitmap(bitmap, width/2-bitmap.getWidth()/2, height+8, paint );



    }
}
