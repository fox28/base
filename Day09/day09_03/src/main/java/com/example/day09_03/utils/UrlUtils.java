package com.example.day09_03.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**请求参数，封装了请求的看，键-值对*/
public class UrlUtils {
    //保存url
    private StringBuilder mUrl;

    /**
     * 获取服务端根地址
     * @param rootUrl：服务端根地址
     * @return
     */
    public UrlUtils url(String rootUrl) {
        mUrl = new StringBuilder(rootUrl);
        return this;
    }

    /**
     * 添加请求参数
     * @param key：请求的键
     * @param value：请求的值
     * @return
     */
    public UrlUtils addParam(String key, String value) {
        if (mUrl == null) {
            return this;
        }
        if (mUrl.indexOf("?") == -1) {
            mUrl.append("?");
        } else {
            mUrl.append("&");
        }
        try {
            mUrl.append(key).append("=").append(URLEncoder.encode(value,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return this;
    }

    /** 返回url*/
    public String build() {
        return mUrl.toString();
    }
}
