package com.qianxunclub.permanent.constants;

import com.qianxunclub.permanent.service.auth.Auth;
import com.qianxunclub.permanent.service.auth.QqService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AuthServiceConstants {

    /**
     *
     */
    QQ("QQ", QqService.class);

    private String authType;
    private Class<? extends Auth> authService;

    public String getAuthType() {
        return authType;
    }

    public Class<? extends Auth> getAuthService() {
        return authService;
    }
}
