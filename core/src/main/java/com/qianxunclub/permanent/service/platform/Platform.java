package com.qianxunclub.permanent.service.platform;

import com.qianxunclub.permanent.constants.PlatformConstants;
import com.qianxunclub.permanent.repository.entity.PlatformEntity;
import com.qianxunclub.permanent.service.platform.data.PlatformOauth;
import com.qianxunclub.permanent.service.platform.data.PlatformUserInfo;

/**
 * @author zhangbin
 */
public abstract class Platform {

    public PlatformConstants getPlatform() {
        return PlatformConstants.getByService(this.getClass());
    }

    public abstract String authorizeUrl(String state);

    public abstract PlatformOauth oauth(String code);

    public abstract PlatformUserInfo userInfo(PlatformEntity platformEntity);

}
