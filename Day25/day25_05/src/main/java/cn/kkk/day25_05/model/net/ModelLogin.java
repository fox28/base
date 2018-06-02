package cn.kkk.day25_05.model.net;

import android.content.Context;

import cn.kkk.day25_05.I;
import cn.kkk.day25_05.bean.UserBean;
import cn.kkk.day25_05.model.utils.OkUtils;


/**
 * Created by apple on 2017/3/23.
 */

public class ModelLogin extends ModelBase implements IModelLogin {
    @Override
    public void login(Context context, String userName, String password, OnCompleteListener<UserBean> listener) {
        OkUtils<UserBean> utils = new OkUtils<>(context);
        utils.url(I.SERVER_URL)
                .addParam(I.KEY_REQUEST, I.REQUEST_LOGIN)
                .addParam(I.User.USER_NAME, userName)
                .addParam(I.User.PASSWORD, password)
                .targetClass(UserBean.class)
                .execute(listener);
    }
}
