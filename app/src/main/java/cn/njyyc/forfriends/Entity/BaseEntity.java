package cn.njyyc.forfriends.Entity;

import java.io.Serializable;

/**
 * Created by MaRufei
 * on 2018/6/23.
 * Email: www.867814102@qq.com
 * Phone：13213580912
 * Purpose:TODO
 * update：
 */
public class BaseEntity implements Serializable{

    /**
     * code : 1
     * msg : 短信验证码发送成功！
     */

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
