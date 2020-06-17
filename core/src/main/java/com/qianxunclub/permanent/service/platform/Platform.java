package com.qianxunclub.permanent.service.platform;

import com.qianxunclub.permanent.configuration.CoreException;
import com.qianxunclub.permanent.constants.PlatformConstants;
import com.qianxunclub.permanent.repository.entity.PlatformEntity;
import com.qianxunclub.permanent.service.platform.data.PlatformOauth;
import com.qianxunclub.permanent.service.platform.data.PlatformUserInfo;

/**
 * @author zhangbin
 */
public abstract class Platform {

    /**
     * 获取当前授权方式
     *
     * @return
     */
    public PlatformConstants getPlatform() {
        return PlatformConstants.getByService(this.getClass());
    }

    /**
     * 获取授权 URL
     *
     * @param state
     * @return
     * @throws CoreException
     */
    public abstract String authorizeUrl(String state) throws CoreException;

    /**
     * 授权成功获取授权信息
     *
     * @param code
     * @return
     * @throws CoreException
     */
    public abstract PlatformOauth oauth(String code) throws CoreException;

    /**
     * 依据 token 获取用户信息
     *
     * @param platformEntity
     * @return
     */
    public abstract PlatformUserInfo userInfo(PlatformEntity platformEntity) throws CoreException;

}
