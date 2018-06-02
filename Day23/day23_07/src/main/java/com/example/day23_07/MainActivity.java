package com.example.day23_07;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    /**
     * 步骤1：编写服务端的HTML代码、（存放在assets文件夹中）
     */

    WebView mWebView;
    EditText metContent;
    Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initHandler();
    }

    private void initHandler() {
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                String text = msg.obj.toString();// 从主线程接受消息、、、从消息队列接受数据
                metContent.setText(text);


            }
        };
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.webView);
        metContent = (EditText) findViewById(R.id.et);

        /**
         * 步骤2：设置WebView支持JavaScript
         */
        mWebView.getSettings().setJavaScriptEnabled(true);


        /**
         * 步骤3：设置与JavaScript的接口
         */
        mWebView.addJavascriptInterface(new MyClass(), "day23_07");

//        mWebView.loadUrl("http://10.0.2.2/android.html");
        mWebView.loadUrl("file:///android_asset/android.html");
    }


    /**
     * 步骤4：定义runOnJs方法获取JavaScript发送的数据
     */
    class MyClass{

        // runOnJs方法被JavaScript调用
        @JavascriptInterface
        public void runOnJs(String string) {
            Message msg = Message.obtain(); // 创建一个消息对象
            msg.obj = string;
            mHandler.sendMessage(msg); // 将该消息发送至主线程，目的是在主线程中显示JavaScript中传递过来的字符串
        }

    }

    public void sendDataToHtml(View view) {

        /**
         * 步骤5、向JavaScript发送数据
         */
        String string = metContent.getText().toString(); // android中的Activity，获取编辑空间中的字符串
        mWebView.loadUrl("javascript:get4Android('"+string+"')"); // 调用页面中的JavaScript函数，并向该函数传递数据
    }

}
