package com.qianxunclub.permanent.service.platform;

import com.qianxunclub.permanent.configuration.OauthConfiguration;
import com.qianxunclub.permanent.constants.AuthConstants;
import com.qianxunclub.permanent.constants.AuthConstants.QqApi;
import com.qianxunclub.permanent.repository.entity.PlatformEntity;
import com.qianxunclub.permanent.service.platform.data.PlatformOauth;
import com.qianxunclub.permanent.service.platform.data.PlatformUserInfo;
import com.qianxunclub.permanent.utils.HttpUtil;
import com.qianxunclub.permanent.utils.JsonUtil;
import java.io.UnsupportedEncodingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class QqService extends Platform {

    private final OauthConfiguration oauthConfiguration;

    @Override
    public String authorizeUrl(String state) {
        try {
            return String.format(
                QqApi.AUTHORIZE,
                oauthConfiguration.getQq().getClientId(),
                URLEncoder.encode(oauthConfiguration.getQq().getRedirectUrl(), "UTF-8"),
                state);
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

    public PlatformOauth accessToken(String code) {
        final String regex = "^access_token=(\\w+)&expires_in=(\\w+)&refresh_token=(\\w+)$";
        Map<String, String> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", oauthConfiguration.getQq().getClientId());
        params.put("client_secret", oauthConfiguration.getQq().getClientSecret());
        params.put("grant_type", "authorization_code");
        params.put("redirect_uri", oauthConfiguration.getQq().getRedirectUrl());
        String response = HttpUtil.httpGet(AuthConstants.QqApi.TOKEN, params);
        Matcher m = Pattern.compile(regex).matcher(response);
        PlatformOauth platformOauth = null;
        if (m.find()) {
            platformOauth = new PlatformOauth();
            platformOauth.setAccessToken(m.group(1));
            platformOauth.setExpiresIn(m.group(2));
            platformOauth.setRefreshToken(m.group(3));
            platformOauth.setPlatform(this.getPlatform().getPlatformName());
        }
        return platformOauth;
    }

    @Override
    public PlatformOauth oauth(String code) {
        PlatformOauth platformOauth = this.accessToken(code);
        final String regex = "\"openid\"\\s*:\\s*\"(\\w+)\"";
        String openId = null;
        Map<String, String> params = new HashMap<>();
        params.put("access_token", platformOauth.getAccessToken());
        String response = HttpUtil.httpGet(AuthConstants.QqApi.OPEN_ID, params);
        Matcher m = Pattern.compile(regex).matcher(response);
        if (m.find()) {
            openId = m.group(1);
        }
        platformOauth.setOpenId(openId);
        return platformOauth;
    }

    @Override
    public PlatformUserInfo userInfo(PlatformEntity platformEntity) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", platformEntity.getToken());
        params.put("oauth_consumer_key", oauthConfiguration.getQq().getClientId());
        params.put("openid", platformEntity.getOpenId());
        String response = HttpUtil.httpGet(QqApi.GET_USER_INFO, params);
        PlatformUserInfo platformUserInfo = JsonUtil.getGson()
            .fromJson(response, PlatformUserInfo.class);
        platformUserInfo.setAvatarUrl(
            JsonUtil.getGson().fromJson(response, Map.class).get("figureurl_qq_2").toString());
        return platformUserInfo;
    }


}
