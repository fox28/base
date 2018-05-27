package com.example.day07_ex01;

/**
 * Created by apple on 2017/2/22.
 */

public class Image {
    private String title;
    private String path;

    public Image() {
    }

    public Image(String title, String path) {
        this.title = title;
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Image{" +
                "title='" + title + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
