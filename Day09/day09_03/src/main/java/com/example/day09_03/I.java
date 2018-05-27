package com.example.day09_03;

/**
 * Created by yao on 2016/4/20.
 */
public interface I {
    String SERVER_URL ="http://139.196.185.33:8080/SuperQQ3Server/Server";
    String UTF_8 = "utf_8";
    String KEY_REQUEST="request";
    String PAGE_ID = "pageId";
    String PAGE_SIZE = "pageSize";

    interface User{
        String USER_NAME = "userName";
        String NICK = "nick";
        String PASSWORD = "password";
        String CONFIRM_PASSWORD = "confirmPassword";
    }


    interface Contact{
        String NAME = "name";
        String MYUID = "myuid";
        String CUID = "cuid";
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

    String REQUEST_UPLOAD_AVATAR = "upload_avatar";

    String REQUEST_ADD_CONTACT = "add_contact";

    String REQUEST_DOWNLOAD_CONTACT_LIST = "download_contact_list";

    String AVATAR_TYPE = "avatarType";

    String FILE_NAME = "fileName";
}
