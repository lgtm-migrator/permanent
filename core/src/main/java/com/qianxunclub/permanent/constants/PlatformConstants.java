package com.qianxunclub.permanent.constants;

import com.qianxunclub.permanent.service.platform.Platform;
import com.qianxunclub.permanent.service.platform.QqService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PlatformConstants {

    /**
     *
     */
    QQ("QQ", QqService.class);

    private String platformName;
    private Class<? extends Platform> authService;

    public String getPlatformName() {
        return platformName;
    }

    public Class<? extends Platform> getAuthService() {
        return authService;
    }

    public static PlatformConstants getByService(Class<? extends Platform> clazz) {
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
