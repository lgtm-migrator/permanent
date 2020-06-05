package com.qianxunclub.permanent.repository.entity;

import lombok.Data;

@Data
public class OauthEntity {

    private Integer id;
    private String platform;
    private String openId;
    private String token;
    private String expiresIn;
    private String refreshToken;
}
