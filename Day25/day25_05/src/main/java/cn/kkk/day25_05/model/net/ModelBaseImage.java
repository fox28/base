package cn.kkk.day25_05.model.net;

import android.content.Context;
import android.widget.ImageView;

import cn.kkk.day25_05.model.utils.OkImageLoader;


/**
 * Created by apple on 2017/3/23.
 */

public class ModelBaseImage extends ModelBase implements IModelBaseImage {
    @Override
    public void releaseImage() {
        OkImageLoader.release();

    }

    @Override
    public void showImage(Context context, String url, ImageView imageView, int defaultPicIc, boolean isDraggind) {
        OkImageLoader.build(url)
                .imageView(imageView)
                .defaultPicture(defaultPicIc)
                .setDragging(isDraggind)
                .showImage(context);
    }

    @Override
    public void showImage(int width, int height, Context context, String url, ImageView imageView, int defaultPicIc, boolean isDraggind) {
        OkImageLoader.build(url)
                .imageView(imageView)
                .setDragging(isDraggind)
                .defaultPicture(defaultPicIc)
                .width(width)
                .height(height)
                .showImage(context);
    }
}
