package cn.kkk.day25_05.model.net;

import android.content.Context;
import android.os.Message;

import java.io.File;

import cn.kkk.day25_05.I;
import cn.kkk.day25_05.model.utils.OkUtils;

/**
 * Created by apple on 2017/3/23.
 */

public class ModelShowAvatar extends ModelBase implements IModelShowAvatar {
    @Override

    public void downloadAvatar(Context context, String userName, File file, OnCompleteListener<Message> listener) {
        OkUtils<Message> utils = new OkUtils<>(context);
        utils.url(I.DOWNLOAD_AVATAR_URL + userName)
                .downloadFile(file)
                .execute(listener);
    }
}
