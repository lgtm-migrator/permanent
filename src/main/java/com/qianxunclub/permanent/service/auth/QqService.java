package com.qianxunclub.permanent.service.auth;

import com.qianxunclub.permanent.config.OauthConfig;
import com.qianxunclub.permanent.constants.AuthConstants;
import com.qianxunclub.permanent.constants.AuthConstants.QqApi;
import com.qianxunclub.permanent.service.auth.data.OauthToken;
import com.qianxunclub.permanent.service.auth.data.OauthUserInfo;
import com.qianxunclub.permanent.utils.HttpUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class QqService extends Auth {

    private OauthConfig oauthConfig;

    @Override
    public String authorizeUrl(String state) {
        return String.format(AuthConstants.QqApi.AUTHORIZE, oauthConfig.getQq().getClientId(),
            URLEncoder.encode(oauthConfig.getQq().getRedirectUrl()), state);
    }

    public OauthToken accessToken(String code) {
        String regex = "^access_token=(\\w+)&expires_in=(\\w+)&refresh_token=(\\w+)$";
        Map<String, String> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", oauthConfig.getQq().getClientId());
        params.put("client_secret", oauthConfig.getQq().getClientSecret());
        params.put("grant_type", "authorization_code");
        params.put("redirect_uri", oauthConfig.getQq().getRedirectUrl());
        String response = HttpUtil.httpGet(AuthConstants.QqApi.TOKEN, params);
        Matcher m = Pattern.compile(regex).matcher(response);
        OauthToken oauthToken = null;
        if (m.find()) {
            oauthToken = new OauthToken();
            oauthToken.setAccessToken(m.group(1));
            oauthToken.setExpiresIn(m.group(2));
            oauthToken.setRefreshToken(m.group(3));
        }
        return oauthToken;
    }

    @Override
    public OauthToken token(String code) {
        OauthToken oauthToken = this.accessToken(code);
        String regex = "\"openid\"\\s*:\\s*\"(\\w+)\"";
        String openId = null;
        Map<String, String> params = new HashMap<>();
        params.put("access_token", oauthToken.getAccessToken());
        String response = HttpUtil.httpGet(AuthConstants.QqApi.OPEN_ID, params);
        Matcher m = Pattern.compile(regex).matcher(response);
        if (m.find()) {
            openId = m.group(1);
        }
        oauthToken.setOpenId(openId);
        return oauthToken;
    }

    @Override
    public OauthUserInfo userInfo() {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", this.oauthEntity.getToken());
        params.put("oauth_consumer_key", oauthConfig.getQq().getClientId());
        params.put("openid", this.oauthEntity.getOpenId());
        String response = HttpUtil.httpGet(QqApi.GET_USER_INFO, params);
        return null;
    }


}
