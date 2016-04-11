package com.ruoxue.basic.baseApi;

import android.content.Context;

import com.ruoxue.basic.net.NetRequest;
import com.ruoxue.basic.net.interfaces.INet;
import com.ruoxue.basic.net.interfaces.NetI;

import java.util.Map;


/**
 * Created by ruoxue_ye on 2015/12/11.
 */
public class BaseApi {

    private static BaseApi baseApi;

    private BaseApi() {

    }


    public static BaseApi apiInstance() {
        if (baseApi == null) {
            baseApi = new BaseApi();
        }
        return baseApi;
    }


    public   void post(INet iNet, Context context, String url, Class clazz, int tag, boolean flag, Map<String, String> map) {
        NetRequest request = NetRequest.newInstance();
        request.volleyPost(context, iNet, url, map, tag, clazz, flag);
    }




    public   void post(NetI iNet, Context context, String url, Class clazz, int tag, boolean flag, Map<String, String> map) {
//        NetRequest request = NetRequest.newInstance();
//        request.volleyPost(context, iNet, url, map, tag, clazz, flag);
    }
}
