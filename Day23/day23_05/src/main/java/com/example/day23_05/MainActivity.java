package com.example.day23_05;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView mWebView;
    ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.wv);
    }

    public void onClick(View view) {
        mWebView.loadUrl("http://www.baidu.com");
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (mDialog == null) {
                    mDialog = ProgressDialog.show(MainActivity.this, "加载百度首页", "loading...");
                } else {
                    mDialog.show();
                }
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                mDialog.hide();
                super.onPageFinished(view, url);

            }
        });
    }
}
