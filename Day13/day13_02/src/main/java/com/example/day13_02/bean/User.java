package com.example.day13_02.bean;

import java.io.Serializable;

/**
 * Created by apple on 2017/3/4.
 */

public class User implements Serializable {
    private String userName;
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String userName, String password) {

        this.userName = userName;
        this.password = password;
    }

}
