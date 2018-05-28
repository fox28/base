package com.example.day11_06.bean;

/**
 * Created by yao on 2015/11/10.
 */
public class AppBean {
    private String name;
    private String version;
    private int fileSize;
    private String thumb;
    private String apk;
    private String intro;

    /*
     private String name;
    private String version;

    private int fileSize;
     */
    private int photoId;

    public int getPhotoId() {
        return photoId;
    }

    public AppBean(String name, String version, int fileSize, String intro, int photoId) {
        this.name = name;
        this.version = version;
        this.fileSize = fileSize;
        this.intro = intro;
        this.photoId = photoId;
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

    public AppBean() {
    }

    @Override
    public String toString() {
        return "AppBean{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", fileSize=" + fileSize +
                ", thumb='" + thumb + '\'' +
                ", apk='" + apk + '\'' +
                ", intro='" + intro + '\'' +
                '}';
    }
}
