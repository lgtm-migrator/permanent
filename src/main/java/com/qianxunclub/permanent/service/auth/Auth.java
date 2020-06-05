package com.qianxunclub.permanent.service.auth;

import com.qianxunclub.permanent.repository.entity.OauthEntity;
import com.qianxunclub.permanent.service.auth.data.OauthToken;
import com.qianxunclub.permanent.service.auth.data.OauthUserInfo;

/**
 * @author zhangbin
 */
public abstract class Auth {

    protected OauthEntity oauthEntity;

    public Auth init(OauthEntity oauthEntity) {
        this.oauthEntity = oauthEntity;
        return this;
    }

    public abstract String authorizeUrl(String state);

    public abstract OauthToken token(String code);

    public abstract OauthUserInfo userInfo();

}
