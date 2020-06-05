package com.qianxunclub.permanent.service.auth.data;

import lombok.Data;

@Data
public class OauthToken {

    private String platform;
    private String accessToken;
    private String expiresIn;
    private String refreshToken;
    private String openId;

}
