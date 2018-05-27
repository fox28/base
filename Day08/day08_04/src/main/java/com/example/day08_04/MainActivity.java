package com.example.day08_04;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean misStop = false;
    int indexStop = 1; //停止后的数值
    static final int DOWNLOAD_START = 0;
    static final int DOWNLOADING = 1;
    static final int DOWNLOAD_FINISH = 2;
    Handler mWorkThreadHandlrer, mMainThreadHandler;
    ProgressBar mBar;
    TextView mtvProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createWorkThread();
        initView();
        initMainThreadHandler();
    }

    private void initMainThreadHandler() {
        mMainThreadHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case DOWNLOAD_START:
                        Toast.makeText(MainActivity.this, "开始下载", Toast.LENGTH_SHORT).show();
                        break;
                    case DOWNLOADING:
                        mtvProgress.setText(msg.arg1 + "%");
                        mBar.setProgress(msg.arg1);
                        break;
                    case DOWNLOAD_FINISH:
                        Toast.makeText(MainActivity.this, "停止下载", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
    }

    private void initView() {
        mBar = (ProgressBar) findViewById(R.id.bar);
        mtvProgress = (TextView) findViewById(R.id.tvProgress);
    }

    private void createWorkThread() {
        new Thread(){
            @Override
            public void run() {
                // WorkThread 中创建了Looper和MessageQueue对象
                Looper.prepare();

                // mWorkThreadHandlrer在工作线程中创建的（匿名内部类）
                // mWorkThreadHandlrer只为工作线程WorkThread服务: 可以在其他线程（包括主线程）发消息，但是只发到工作线程
                mWorkThreadHandlrer = new Handler(){
                    int i;
                    @Override
                    public void handleMessage(final Message msg) {
                        if (msg.what == DOWNLOAD_START) {
                            /*for (i=1; i<=100 && !misStop; i++) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mBar.setProgress(i);
                                        mtvProgress.setText(i+"%");
                                    }
                                });
                                SystemClock.sleep(30);*/
                            mMainThreadHandler.sendEmptyMessage(DOWNLOAD_START);
                            for (i = indexStop; i<=100 && !misStop; i++) {
                                Message msg2 = Message.obtain();
                                msg2.what = DOWNLOADING;
                                msg2.arg1 = i;
                                indexStop = i;

                                mMainThreadHandler.sendMessage(msg2);
                                SystemClock.sleep(30);
                            }
                            mMainThreadHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                        }

                    }
                };



                // 无限循环， Looper遍历消息队列
                Looper.loop();
            }
        }.start();
    }

    public void startDownload(View view) {
        // 向工作线程的消息队列发送消息
        mWorkThreadHandlrer.sendEmptyMessage(DOWNLOAD_START);
        misStop = false;

        // 重置indexStop的值
        if (indexStop == 100) {
            indexStop = 1;
        }
    }

    /**
     * 单击停止按钮，停止正在下载的操作
     * @param view
     */
    public void stopDownload(View view) {
        misStop = true;
    }
}
