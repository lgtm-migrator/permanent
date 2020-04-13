package com.qianxunclub.permanent.service.login;

public interface Oauth {

    String authorizeUrl(String state);

    String token(String code);

    String openId(String accessToken);
}
