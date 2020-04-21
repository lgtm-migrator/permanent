package com.qianxunclub.permanent.service.auth;

public interface Auth {

    String authorizeUrl(String state);

    String token(String code);

    String openId(String code);

}
