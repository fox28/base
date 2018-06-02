package com.example.day23_06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.webView);

        WebSettings settings = mWebView.getSettings();
        settings.setDefaultTextEncodingName("utf-8");
        settings.setDefaultFontSize(18);
        settings.setBuiltInZoomControls(true);
    }

    public void onLoadHtml(View view) {
        try {
            InputStream is = getAssets().open("article.html");
            StringBuffer sb = new StringBuffer();
            byte[] bytes = new byte[1024 * 8];
            int len;
            while ((len = is.read(bytes)) != -1) {
                String str = new String(bytes, 0, len);
                sb.append(str);
            }
            mWebView.loadDataWithBaseURL(null,sb.toString(),"text/html","utf-8",null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
