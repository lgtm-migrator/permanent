package com.qianxunclub.permanent.service;

import com.qianxunclub.permanent.repository.dao.CustomersDao;
import com.qianxunclub.permanent.repository.dao.PlatformDao;
import com.qianxunclub.permanent.repository.entity.PlatformEntity;
import com.qianxunclub.permanent.service.platform.Platform;
import com.qianxunclub.permanent.service.platform.PlatformFactory;
import com.qianxunclub.permanent.service.platform.data.PlatformOauth;
import com.qianxunclub.permanent.service.platform.data.PlatformUserInfo;
import com.qianxunclub.permanent.utils.JsonUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author zhangbin
 */
@Service
@AllArgsConstructor
public class AuthService {

    private final PlatformDao platformDao;
    private final CustomersService customersService;

    public String auth(String platform) {
        return PlatformFactory.getInstance(platform)
            .authorizeUrl("{\"platform\":\"" + platform + "\"}");
    }

    public void callback(String code, String state) {
        String authType = JsonUtil.getGson().fromJson(state, Map.class).get("platform").toString();
        Platform platform = PlatformFactory.getInstance(authType);
        PlatformOauth platformOauth = platform.oauth(code);
        PlatformEntity platformEntity = platformDao.insertOrUpdate(platformOauth);
        PlatformUserInfo platformUserInfo = platform.userInfo(platformEntity);
        customersService.register(platformUserInfo, platformEntity);
    }
}
