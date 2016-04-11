package xyz.ruoxue.base.net;

import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ruoxue.basic.application.RuoxueApp;
import com.ruoxue.basic.net.interfaces.INet;
import com.ruoxue.basic.ruoxueUtil.SPUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;


/**
 *
 */
public class NetRequest {
    private NetRequest() {

    }

    private static NetRequest newInstance;

    public synchronized static NetRequest newInstance() {
        if (newInstance == null) {
            synchronized (NetRequest.class) {
                newInstance = new NetRequest();
            }
        }
        return newInstance;
    }

    /**
     * xutil 3.0+
     *
     * @param url   请求地址
     * @param clazz 类
     * @param tag   标识
     * @param flag  是否数组
     * @param map   参数
     */
    public void xPost(final Context context, final INet iNet, String url, final Class clazz, final int tag, final boolean flag, final Map<String, String> map) {
        RequestParams params = new RequestParams(url);
        for (Map.Entry<String, String> e : map.entrySet()) {
            params.addBodyParameter(e.getKey(), e.getValue());
        }
        x.http().request(HttpMethod.POST, params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                JsonUtil.success(context, iNet, result, tag, clazz, flag);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                JsonUtil.error(context, iNet, ex.getMessage(), tag);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void xPost(final Context context, final INet iNet, String url, final Class clazz, final int tag, final Map<String, String> map) {
        xPost(context, iNet, url, clazz, tag, true, map);
    }


    /**
     * volley 网络请求在子线程中
     *
     * @param context {@link Context}
     * @param iNet    {@link INet}
     * @param url     网址
     * @param map     {@link Map}
     * @param tag     {@link Integer}
     * @param clazz   {@link Class}
     * @param flag    {@link Boolean}
     */


    public void volleyPost(final Context context, final INet iNet, final String url, final Map<String, String> map, final int tag, final Class clazz, final boolean flag) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JsonUtil.success(context, iNet, response, tag, clazz, flag);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        JsonUtil.error(context, iNet, error.getMessage(), tag);
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        if (map.isEmpty() || map.size() == 0)
                            return super.getParams();
                        return map;

                    }
                };
                request.setTag(url);
                request.setShouldCache(true);
                request.setRetryPolicy(new DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)

                );//cache
                RuoxueApp.getInstance(context.getApplicationContext()).add(request);
                return null;
            }
        }.execute();
    }


    public void volleyPost(final Context context, final INet iNet, final String url, final int tag, final Class clazz, final boolean flag) {
        volleyPost(context, iNet, url, null, tag, clazz, flag);
    }

    public void volleyPost(final Context context, final INet iNet, Map<String, String> map, final String url, final int tag, final Class clazz) {
        volleyPost(context, iNet, url, map, tag, clazz, true);
    }

    public void volleyPost(final Context context, final INet iNet, final String url, final Class clazz, final boolean flag) {
        volleyPost(context, iNet, url, 0, clazz, flag);
    }

    public void volleyPost(final Context context, final INet iNet, final String url, final Class clazz) {
        volleyPost(context, iNet, url, clazz, true);
    }

    public void volleyPost(final Context context, final INet iNet, Map<String, String> map, final String url, final int tag, final boolean flag) {
        volleyPost(context, iNet, url, map, tag, Object.class, flag);
    }

    /**
     * @param context 上下文 {@link Context}
     * @param iNet    网络回调接口{@link INet}
     * @param url     网址  {@link java.net.URL}
     * @param map     参数
     * @param tag     标识
     * @param clazz   返回bean
     * @param flag    是否数组
     */
    public void volleyGet(final Context context, final INet iNet, final String url, final Map<String, String> map, final int tag, final Class clazz, final boolean flag) {
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JsonUtil.success(context, iNet, response, tag, clazz, flag);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                JsonUtil.error(context, iNet, error.getMessage(), tag);
            }
        });
        request.setTag(url);
        request.setShouldCache(true);
        request.setRetryPolicy(new DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));//cache
        RuoxueApp.getInstance(context.getApplicationContext()).add(request);
    }


    public void volleyCookie(final Context context, final INet iNet, final String url, final Map<String, String> map, final int tag, final Class clazz, final boolean flag) {
        //发起请求
        CookieVolley jsonObjectPostRequest = new CookieVolley(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    SPUtils.put(context, "COOK", jsonObject.getString("Cookie"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                JsonUtil.success(context, iNet, jsonObject.toString(), tag, clazz, flag);
//                try {
//                    if (jsonObject.get("status").equals("success")) {
//                        //登录成功
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                JsonUtil.error(context, iNet, error.getMessage(), tag);
            }
        }, map);
        String localCookieStr = SPUtils.get(context, "COOK", "").toString();
        if (!localCookieStr.equals("")) {
            jsonObjectPostRequest.setSendCookie(localCookieStr);//向服务器发起post请求时加上cookie字段
        }
        RuoxueApp.getInstance(context.getApplicationContext()).add(jsonObjectPostRequest);
    }
}