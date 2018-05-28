package com.example.day14_02.bean;

/**
 * Created by yao on 2015/11/10.
 */
public class AppBean {
    private String name;
    private String version;
    private String thumb;
    private int fileSize;
    private String apk;
    private String intro;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getApk() {
        return apk;
    }

    public void setApk(String apk) {
        this.apk = apk;
    }

    public AppBean() {
    }

    @Override
    public String toString() {
        return "AppBean{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", thumb='" + thumb + '\'' +
                ", fileSize=" + fileSize +
                ", apk='" + apk + '\'' +
                ", intro='" + intro + '\'' +
                '}';
    }
}
