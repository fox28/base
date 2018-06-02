package cn.kkk.day25_05.model.net;


import cn.kkk.day25_05.model.utils.OkUtils;

/**
 * Created by apple on 2017/3/23.
 */

public class ModelBase implements IModelBase {
    @Override
    public void release() {
        OkUtils.release();
    }
}
