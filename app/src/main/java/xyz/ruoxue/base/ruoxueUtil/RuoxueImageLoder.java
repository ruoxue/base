package xyz.ruoxue.base.ruoxueUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.ruoxue.basic.application.RuoxueApp;


/**
 * Created by ruoxue_ye on 2015/9/28.
 */
public class RuoxueImageLoder {

    public static void disPlay(String url, ImageView iv,Context context) {
        disPlay(url, iv, RuoxueApp.initOption(),context);
    }

    public static void disPlay(String url, ImageView iv, DisplayImageOptions options,Context context) {
        disPlay(url, iv, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {

            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        },context);
    }

    public static void disPlay(String url, ImageView iv, DisplayImageOptions options, ImageLoadingListener listener,Context context) {
        if (RuoxueApp.initImageLoder() != null) {
            try {
                RuoxueApp.initImageLoder().displayImage(url, iv, options, listener);
            } catch (IllegalStateException e) {
                RuoxueApp.initImageLoader(context);
            }
        } else {
            ImageLoader.getInstance().displayImage(url, iv, options, listener);

        }


    }


}
