package com.example.day09_01.bean;

/**
 * Created by yao on 2017/2/15.
 */

public class UserBean {

    /**
     * id : 90
     * result : ok
     * userName : a
     * nick : 尧玮
     * password : a
     * avatar : user_avatar/a.jpg
     * groups : null
     * header : null
     * latitude : 0.0
     * longitude : 0.0
     * unreadMsgCount : 0
     */

    private int id;
    private String result;
    private String userName;
    private String nick;
    private String password;
    private String avatar;
    private String groups;
    private String header;
    private double latitude;
    private double longitude;
    private int unreadMsgCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getUnreadMsgCount() {
        return unreadMsgCount;
    }

    public void setUnreadMsgCount(int unreadMsgCount) {
        this.unreadMsgCount = unreadMsgCount;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", result='" + result + '\'' +
                ", userName='" + userName + '\'' +
                ", nick='" + nick + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", groups='" + groups + '\'' +
                ", header='" + header + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", unreadMsgCount=" + unreadMsgCount +
                '}';
    }
}
