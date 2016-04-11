package com.ruoxue.basic.net;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.ruoxue.basic.R;
import com.ruoxue.basic.net.interfaces.INet;
import com.ruoxue.basic.netConfig.Config;


/**
 * Created by ruoxue_ye on 2015/12/11.
 */
public class JsonUtil {
    public static void success(Context context, INet iNet, String response, int tag, Class clazz, boolean flag) {

        JSONObject jsonObject = JSONObject.parseObject(response);//转化为json
        switch (jsonObject.getIntValue(Config.NetStatus.STATUS)) {
            case Config.NetStatus.ZERO:
                parseError(iNet, tag, context, jsonObject);
                break;
            case Config.NetStatus.FIRST:
                parseSuccess(context, iNet, tag, clazz, flag, jsonObject);
                break;
            case Config.NetStatus.SECOND:
                parseLogout(context);
                break;
            default:
                parseLogout(context);

                break;
        }
    }

    private static void parseError(INet iNet, int tag, Context context, JSONObject jsonObject) {
        String s = jsonObject.getString(Config.NetStatus.MSG);
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        iNet.response(tag, null);
    }

    private static void parseLogout(Context context) {
        new AlertDialog.Builder(context).setTitle(R.string.error).setMessage(R.string.no_logout).setPositiveButton(android.R.string.cancel, null).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show().setCanceledOnTouchOutside(false);


    }

    /**
     * success
     *
     * @param iNet
     * @param tag
     * @param clazz
     * @param flag
     * @param jsonObject
     */
    private static void parseSuccess(Context context, final INet iNet, final int tag, Class clazz, boolean flag, JSONObject jsonObject) {
        final Object o;
        String s = jsonObject.getString(Config.NetStatus.DATA);
        if (clazz == null) {
            o = s;
        } else {
            if (flag) {
                o = JSONObject.parseArray(s, clazz);
            } else {
                o = JSONObject.parseObject(s, clazz);
            }
        }


        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    iNet.response(tag, o);
                }
            });
        } else {
//            Message message=Message.obtain();
        }

    }

    public static void error(Context context, final INet iNet, String error, final int tag) {//处理异常  一般为网络异常
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    iNet.response(tag, null);
                }
            });
        }

    }
}
