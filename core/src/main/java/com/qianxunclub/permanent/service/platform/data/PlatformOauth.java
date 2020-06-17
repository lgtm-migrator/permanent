package com.qianxunclub.permanent.service.platform.data;

import lombok.Data;

/**
 * @author zhangbin
 */
@Data
public class PlatformOauth {

    private String platform;
    private String accessToken;
    private String expiresIn;
    private String refreshToken;
    private String openId;

}
