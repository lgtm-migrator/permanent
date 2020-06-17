package com.qianxunclub.permanent.constants;

/**
 * @author zhangbin
 */
public class AuthConstants {

    private AuthConstants() {

    }

    public static class QqApi {

        private QqApi() {
        }

        public static final String AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=%s&redirect_uri=%s&state=%s";
        public static final String TOKEN = "https://graph.qq.com/oauth2.0/token";
        public static final String OPEN_ID = "https://graph.qq.com/oauth2.0/me";
        public static final String GET_USER_INFO = "https://graph.qq.com/user/get_user_info";
    }

    public static class WxssApi {

        private WxssApi() {
        }

        public static final String AUTHORIZE = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    }


}
