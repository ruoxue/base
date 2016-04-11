package com.ruoxue.basic.application;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.ruoxue.basic.R;
import com.ruoxue.basic.exception.UnKnowException;
import com.ruoxue.basic.ruoxueUtil.AppUtils;
import com.ruoxue.basic.ruoxueUtil.L;
import com.zhy.changeskin.SkinManager;

import org.xutils.x;

import java.util.Stack;


/**
 * Created by ruoxue_ye on 2015/12/11.
 */
public class RuoxueApp extends Application {

    private static RequestQueue requestQueue;
    private static DisplayImageOptions options;
    private static ImageLoader imageLoader;
    private static RuoxueApp newInstance;

    private static Stack<Context> ctx;

    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().init(this);
        newInstance = this;
        x.Ext.init(this);
        x.Ext.setDebug(AppUtils.isDubug(this));
        requestQueue = Volley.newRequestQueue(this);
        ctx = new Stack<>();
        catchUnCatchException();//捕获不可预知异常  正式是启用
    }

    public static void putContext(Context context) {
        ctx.push(context);
    }

    public static Stack<Context> getContext() {
        for (Context context : ctx) {
            L.i("ruoxue", context.getPackageName());
        }
        return ctx;
    }


    public static RuoxueApp getNewInstance() {
        return newInstance;
    }

    /*
    volley
     */
    public static RequestQueue getInstance(Context context) {
        if (requestQueue == null) {
            synchronized (RequestQueue.class) {
                requestQueue = Volley.newRequestQueue(context.getApplicationContext());
            }
        }
        return requestQueue;
    }


    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context.getApplicationContext())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
//                .writeDebugLogs() // Remove for release app
                .build();
        ImageLoader.getInstance().init(config);
    }

    public static DisplayImageOptions initOption() {
        if (options == null) {

            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.mipmap.ic_launcher)
                    .showImageForEmptyUri(R.mipmap.ic_launcher)
                    .showImageOnFail(R.mipmap.ic_launcher)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)     //设置图片的解码类型
                    .build();
        }
        return options;
    }

    public static ImageLoader initImageLoder() {
        if (imageLoader == null) {
            synchronized (ImageLoader.class) {
                imageLoader = ImageLoader.getInstance();
            }
        }
        return imageLoader;
    }


    private void catchUnCatchException() {
        UnKnowException unKnowException = UnKnowException.getInstance();
        unKnowException.init(this);
        Thread.setDefaultUncaughtExceptionHandler(unKnowException);

    }
}
