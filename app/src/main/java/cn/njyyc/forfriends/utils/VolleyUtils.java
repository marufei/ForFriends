package cn.njyyc.forfriends.utils;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MaRufei
 * time on 2017/8/17
 * Phone 13213580912
 * Email 867814102@qq.com
 */

public class VolleyUtils {
    private static String TAG="VolleyUtils";
    private static VolleyUtils volleyUtils;
    private RequestQueue mRequestQueue;

    private static Context mCtx;
    private static final int TIME_OUT = 10 * 1000;//设置超时时间

    private VolleyUtils(Context mCtx) {
        this.mCtx = mCtx;
        mRequestQueue = getRequestQueue();
    }

    public static void setTimeOut(StringRequest stringRequest) {
        if (stringRequest != null) {
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    TIME_OUT,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        }
    }

    public RequestQueue getRequestQueue() {

        if (mRequestQueue == null) {

            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());

        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {

        getRequestQueue().add(req);

    }

    public static synchronized VolleyUtils getInstance(Context context){

        if(volleyUtils == null){

            volleyUtils=new VolleyUtils(context);

        }

        return volleyUtils;

    }

    public static Map<String,String> addParams(Map map){

        map.put("timeStamp",getTimestamp());
        MyUtils.Loge(TAG,"时间戳："+getTimestamp());
        map.put("device","android");
        map.put("uuid",MyUtils.getMacAddress());
        map.put("sign",getSign());
        return map;
    }

    public static String getTimestamp() {
        String timestamp = String.valueOf(new Date().getTime() / 1000);
        return timestamp;
    }

    public static String getSign() {
        String sign = "bMdzRybw5DMIt7Ia" + getTimestamp();
        sign = MyUtils.md5(sign);
        MyUtils.Loge(TAG,"sign--1--"+sign);
        String sign1=sign.substring(20,30);
        MyUtils.Loge(TAG,"sign1--2--"+sign1);
        String sign2=sign.substring(5,15);
        MyUtils.Loge(TAG,"sign2--2--"+sign2);
        sign=sign1+sign2;
        return sign;
    }
}
