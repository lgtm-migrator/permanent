package com.qianxunclub.permanent.constants;

import com.qianxunclub.permanent.service.auth.Auth;
import com.qianxunclub.permanent.service.auth.QqService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PlatformConstants {

    /**
     *
     */
    QQ("QQ", QqService.class);

    private String platformName;
    private Class<? extends Auth> authService;

    public String getPlatformName() {
        return platformName;
    }

    public Class<? extends Auth> getAuthService() {
        return authService;
    }

    public static PlatformConstants getByService(Class<? extends Auth> clazz) {
        PlatformConstants[] platformConstants = PlatformConstants.values();
        for (int i = 0; i < platformConstants.length; i++) {
            PlatformConstants platform = platformConstants[i];
            if (platform.getAuthService().getClass().isAssignableFrom(clazz.getClass())) {
                return platform;
            }
        }
        return null;
    }
}
