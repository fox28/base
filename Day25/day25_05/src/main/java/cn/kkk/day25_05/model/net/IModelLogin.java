package cn.kkk.day25_05.model.net;

import android.content.Context;

import cn.kkk.day25_05.bean.UserBean;
import cn.kkk.day25_05.model.utils.OkUtils;


/**
 * Created by apple on 2017/3/23.
 */

public interface IModelLogin extends IModelBase {
    void login(Context context, String userName, String password,
              OnCompleteListener<UserBean> listener);
}
