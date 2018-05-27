package cn.ucai.day04_03;

import android.app.ProgressDialog;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void defaultProgressDialog(View v) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("这是缺省对话框的标题");
        dialog.setMessage("这是缺省对话框的message");
        dialog.show();
        dialog.setCancelable(false);
        new Thread(){
            @Override
            public void run() {
                SystemClock.sleep(3000);
                dialog.dismiss();
            }
        }.start();
    }

    /**
     * 缺省对话框
     * 这种情况下，"按back键不能关闭对话框的功能、点击对话框以外也无法关闭对话框"？？？
     * @param view
     *//*
    public void defaultProgressDialog(View view) {
        ProgressDialog.show(this, "这是缺省对话框的标题",
                "这是缺省对话框的message");
    }*/

    public void ProgressDialog(View view) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMax(100);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.show();
        new Thread(){
            @Override
            public void run(){
                for (int i=1; i<=100; i++) {
                    dialog.setProgress(i);
                    //SystemClock.sleep(50);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                dialog.dismiss();
            }
        }.start();
    }

    public void showProgressDialog(View v) {
        final ProgressDialog dialog = ProgressDialog.show(this, "加载百度页", "loading");
        new Thread() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                dialog.dismiss();
            }
        }.start();

    }


}
