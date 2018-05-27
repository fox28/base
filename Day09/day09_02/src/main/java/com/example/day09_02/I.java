package com.example.day09_02;

/**
 * Created by yao on 2016/4/20.
 */
public interface I {
//    String SERVER_URL="http://10.0.2.2:8080/SuperQQ3Server/Server";
    String SERVER_URL ="http://139.196.185.33:8080/SuperQQ3Server/Server";
    String UTF_8 = "utf_8";
    String KEY_REQUEST="request";

    interface User{
        String USER_NAME = "userName";
        String NICK = "nick";
        String PASSWORD = "password";
        String CONFIRM_PASSWORD = "confirmPassword";
    }
    /** 注册请求*/
    String REQUEST_REGISTER="register";
    /**
     * 取消注册
     */
    String REQUEST_UNREGISTER = "unregister";

    /**
     * 登陆请求
     */
    String REQUEST_LOGIN = "login";
}
