package cn.njyyc.forfriends.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import cn.njyyc.forfriends.Entity.SmsEntity;
import cn.njyyc.forfriends.Https.HttpsAddress;
import cn.njyyc.forfriends.R;
import cn.njyyc.forfriends.utils.KeyUtils;
import cn.njyyc.forfriends.utils.MyUtils;
import cn.njyyc.forfriends.utils.SaveUtils;
import cn.njyyc.forfriends.utils.VolleyUtils;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_login;
    private String TAG="LoginActivity";
    private Button btn_sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        btn_login=findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        btn_sms=findViewById(R.id.btn_sms);
        btn_sms.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_sms:
                getSms();
                break;
        }
    }

    /**
     * 获取验证码
     */
    public void getSms(){
        String url= HttpsAddress.BASE_URL+HttpsAddress.SMS;
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                MyUtils.Loge(TAG,"response:"+response);
                Gson gson=new Gson();
                SmsEntity smsEntity=gson.fromJson(response,SmsEntity.class);
                if(smsEntity.getCode()==1){
                    SaveUtils.setString(KeyUtils.sms_token,smsEntity.getData().getSms_token());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap();
                map.put("type","1");
                map.put("phone","13213580912");
                map=VolleyUtils.addParams(map);
                return map;
            }
        };
        VolleyUtils.setTimeOut(stringRequest);
        VolleyUtils.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);
    }

    /**
     * 短信登录
     */
    public void login(){
        String url=HttpsAddress.BASE_URL+HttpsAddress.SMS_LOGIN;
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                MyUtils.Loge(TAG,"response:"+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap();
                map.put("msgCode","569061");
                map.put("phone","13213580912");
                map.put("sms_token",SaveUtils.getString(KeyUtils.sms_token));
                map=VolleyUtils.addParams(map);
                return map;
            }
        };
        VolleyUtils.setTimeOut(stringRequest);
        VolleyUtils.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);
    }


}
