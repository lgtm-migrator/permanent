package com.qianxunclub.permanent.service.auth;

import com.qianxunclub.permanent.constants.AuthConstants;
import com.qianxunclub.permanent.utils.HttpUtil;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class QqService implements Auth {

    @Override
    public String authorizeUrl(String state) {
        return String.format(AuthConstants.QQ_AUTH_URL, AuthConstants.QQ_CLIENT_ID, URLEncoder.encode(AuthConstants.QQ_REDIRECT_URL), state);
    }

    @Override
    public String token(String code) {
        String accessToken = null;
        String regex = "^access_token=(\\w+)&expires_in=(\\w+)&refresh_token=(\\w+)$";
        Map<String, String> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", AuthConstants.QQ_CLIENT_ID);
        params.put("client_secret", AuthConstants.QQ_CLIENT_SECRET);
        params.put("grant_type", "authorization_code");
        params.put("redirect_uri", AuthConstants.QQ_REDIRECT_URL);
        String response = HttpUtil.httpGet(AuthConstants.QQ_TOKEN_URL, params);
        Matcher m = Pattern.compile(regex).matcher(response);
        if (m.find()) {
            accessToken = m.group(1);
        }

        return accessToken;
    }

    @Override
    public String openId(String code) {
        String token = this.token(code);
        String regex = "\"openid\"\\s*:\\s*\"(\\w+)\"";
        String openId = null;
        Map<String, String> params = new HashMap<>();
        params.put("access_token", token);
        String response = HttpUtil.httpGet(AuthConstants.QQ_TOKEN_INFO_URL, params);
        Matcher m = Pattern.compile(regex).matcher(response);
        if (m.find()) {
            openId = m.group(1);
        }
        return openId;
    }


}
