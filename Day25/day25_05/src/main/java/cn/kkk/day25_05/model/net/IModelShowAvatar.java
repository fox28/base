package cn.kkk.day25_05.model.net;

import android.content.Context;
import android.os.Message;

import java.io.File;

/**
 * Created by apple on 2017/3/23.
 */

public interface IModelShowAvatar extends IModelBase {
    void downloadAvatar(Context context, String userName, File file, OnCompleteListener<Message> listener);
}
