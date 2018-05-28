package com.example.day18_01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by apple on 2017/3/9.
 */

public class BasicDraw extends View {
    public BasicDraw(Context context) {
        super(context);
    }

    public BasicDraw(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 设置画布背景：黄色
        canvas.drawColor(Color.GRAY);

        Paint paint = new Paint(); // 创建画笔对象
        paint.setColor(Color.RED); // 设置画笔颜色
        paint.setStrokeWidth(22); // 设置笔触

        canvas.drawPoint(52,53,paint);

        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);
        canvas.drawLine(30,60,330,60,paint);


        //画一个半径为150的实心，白色的圆
        canvas.drawText("画圆",330,330,paint);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(270,330,150,paint);


        canvas.drawCircle(270, 730, 200,paint);


        //画一个矩形
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(360, 360, 680, 680, paint);




        //画一个圆角矩形

        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        canvas.drawRoundRect(200,700,500,999,20,15,paint);
    }
}
