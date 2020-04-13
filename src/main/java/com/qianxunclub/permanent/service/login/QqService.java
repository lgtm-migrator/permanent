package com.qianxunclub.permanent.service.login;

import com.qianxunclub.permanent.constants.OauthConstants;
import com.qianxunclub.permanent.utils.HttpUtil;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class QqService implements Oauth {

    @Override
    public String authorizeUrl(String state) {
        return String.format(OauthConstants.QQ_AUTH_URL, OauthConstants.QQ_CLIENT_ID, URLEncoder.encode(OauthConstants.QQ_REDIRECT_URL), state);
    }

    @Override
    public String token(String code) {
        String accessToken = null;
        String regex = "^access_token=(\\w+)&expires_in=(\\w+)&refresh_token=(\\w+)$";
        Map<String, String> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", OauthConstants.QQ_CLIENT_ID);
        params.put("client_secret", OauthConstants.QQ_CLIENT_SECRET);
        params.put("grant_type", "authorization_code");
        params.put("redirect_uri", URLEncoder.encode(OauthConstants.QQ_REDIRECT_URL));
        String response = HttpUtil.httpGet(OauthConstants.QQ_TOKEN_URL, params);
        Matcher m = Pattern.compile(regex).matcher(response);
        if (m.find()) {
            accessToken = m.group(1);
        }

        return accessToken;
    }

    @Override
    public String openId(String accessToken) {
        String regex = "\"openid\"\\s*:\\s*\"(\\w+)\"";
        String openId = null;
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        String response = HttpUtil.httpGet(OauthConstants.QQ_TOKEN_INFO_URL, params);
        Matcher m = Pattern.compile(regex).matcher(response);
        if (m.find()) {
            openId = m.group(1);
        }
        return openId;
    }


}
