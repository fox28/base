package com.example.day09_02.bean;

import java.io.Serializable;

/**
 * 获取服务端的响应实体
 * Created by apple on 2017/2/24.
 */

public class MessageBean implements Serializable{


    private boolean success;// 响应是否成功：true成功，false失败
    private String msg; // 返回的字符串

    public MessageBean() {
    }

    public MessageBean(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                '}';
    }
}
