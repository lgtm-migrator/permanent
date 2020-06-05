package com.qianxunclub.permanent.service.auth;

import com.qianxunclub.permanent.PermanentApplication;
import com.qianxunclub.permanent.constants.PlatformConstants;
import com.qianxunclub.permanent.repository.entity.OauthEntity;

/**
 * @author zhangbin
 */
public class AuthFactory {

    public static Auth getInstance(String platform) {
        Class<? extends Auth> auth = PlatformConstants.valueOf(platform).getAuthService();
        return PermanentApplication.context.getBean(auth);
    }

    public static Auth getInstance(OauthEntity oauthEntity) {
        Class<? extends Auth> auth = PlatformConstants.valueOf(oauthEntity.getPlatform())
            .getAuthService();
        return PermanentApplication.context.getBean(auth).init(oauthEntity);
    }
}
