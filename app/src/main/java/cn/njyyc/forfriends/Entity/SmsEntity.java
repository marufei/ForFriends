package cn.njyyc.forfriends.Entity;

/**
 * Created by MaRufei
 * on 2018/6/23.
 * Email: www.867814102@qq.com
 * Phone：13213580912
 * Purpose:TODO
 * update：
 */
public class SmsEntity extends BaseEntity {


    /**
     * data : {"sms_token":"1b890548-7150-59fa-a50a"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * sms_token : 1b890548-7150-59fa-a50a
         */

        private String sms_token;

        public String getSms_token() {
            return sms_token;
        }

        public void setSms_token(String sms_token) {
            this.sms_token = sms_token;
        }
    }
}
