package com.example.day15_06.bean;

/**
 * Created by yao on 2016/9/22.
 */

public class ApkBean {
    private int version;
    private String apk;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getApk() {
        return apk;
    }

    public void setApk(String apk) {
        this.apk = apk;
    }

    @Override
    public String toString() {
        return "ApkBean{" +
                "version=" + version +
                ", apk='" + apk + '\'' +
                '}';
    }
}
