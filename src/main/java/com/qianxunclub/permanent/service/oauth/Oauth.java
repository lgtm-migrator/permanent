package com.qianxunclub.permanent.service.oauth;

public interface Oauth {

    String authorizeUrl(String state);

    String token(String code);

    String openId(String accessToken);

    String callback(String code, String state);
}
