package cn.kkk.day25_05.model.net;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by apple on 2017/3/23.
 */

public interface IModelBaseImage extends IModelBase {
    void releaseImage();

    void showImage(Context context, String url, ImageView imageView, int defaultPicIc, boolean isDraggind);

    void showImage(int width, int height, Context context, String url,
                   ImageView imageView, int defaultPicIc, boolean isDraggind);

}
