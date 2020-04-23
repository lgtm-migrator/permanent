package com.qianxunclub.permanent.constants;

public class AuthConstants {
    public static String QQ_CLIENT_ID = "101372806";
    public static String QQ_CLIENT_SECRET = "618ebc3de5734fb7623da8037096836d";
    public static String QQ_REDIRECT_URL = "http://permanent.qianxunclub.com:8080/auth";
    public static String QQ_AUTH_URL = "https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=%s&redirect_uri=%s&state=%s";
    public static String QQ_TOKEN_URL = "https://graph.qq.com/oauth2.0/token";
    public static String QQ_TOKEN_INFO_URL = "https://graph.qq.com/oauth2.0/me";
}
