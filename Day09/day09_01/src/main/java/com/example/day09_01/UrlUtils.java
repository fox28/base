package com.example.day09_01;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by apple on 2017/2/24.
 */

public class UrlUtils {
    private StringBuilder mUrl;// 存放当前的url

    public UrlUtils url(String url) {
        mUrl = new StringBuilder(url);
        return  this;
    }

    public UrlUtils addParam(String key, String value) {
        if (mUrl.indexOf("?") == -1) {
            mUrl.append("?");

        } else {
            mUrl.append("&");
        }
        try {
            mUrl.append(key).append("=").append(URLEncoder.encode(value,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return this;
    }

    public String build() {
        return mUrl.toString();
    }

}
