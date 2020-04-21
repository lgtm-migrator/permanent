package com.qianxunclub.permanent.service.auth;

import com.qianxunclub.permanent.PermanentApplication;
import com.qianxunclub.permanent.constants.AuthServiceConstants;

public class AuthFactory {

    public static Auth getInstance(String authType) {
        Class<? extends Auth> auth = AuthServiceConstants.valueOf(authType).getAuthService();
        return PermanentApplication.context.getBean(auth);
    }
}
