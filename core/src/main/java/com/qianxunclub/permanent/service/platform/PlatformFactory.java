package com.qianxunclub.permanent.service.platform;

import com.qianxunclub.permanent.PermanentApplication;
import com.qianxunclub.permanent.constants.PlatformConstants;

/**
 * @author zhangbin
 */
public class PlatformFactory {

    private PlatformFactory() {
    }

    public static Platform getInstance(String platform) {
        Class<? extends Platform> auth = PlatformConstants.valueOf(platform.toUpperCase())
            .getAuthService();
        return PermanentApplication.context.getBean(auth);
    }

    public static Platform getInstance(PlatformConstants platformConstants) {
        Class<? extends Platform> auth = platformConstants.getAuthService();
        return PermanentApplication.context.getBean(auth);
    }
}
