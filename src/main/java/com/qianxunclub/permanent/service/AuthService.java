package com.qianxunclub.permanent.service;

import com.qianxunclub.permanent.constants.BaseConstants;
import com.qianxunclub.permanent.model.CustomersInfo;
import com.qianxunclub.permanent.model.SessionInfo;
import com.qianxunclub.permanent.repository.dao.PlatformDao;
import com.qianxunclub.permanent.repository.entity.PlatformEntity;
import com.qianxunclub.permanent.service.platform.Platform;
import com.qianxunclub.permanent.service.platform.PlatformFactory;
import com.qianxunclub.permanent.service.platform.data.PlatformOauth;
import com.qianxunclub.permanent.service.platform.data.PlatformUserInfo;
import com.qianxunclub.permanent.utils.JsonUtil;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
    private SessionService sessionService;

    public String auth(String platform) {
        return PlatformFactory.getInstance(platform)
            .authorizeUrl("{\"platform\":\"" + platform + "\"}");
    }

    public CustomersInfo callback(String code, String state) {
        String authType = JsonUtil.getGson().fromJson(state, Map.class).get("platform").toString();
        Platform platform = PlatformFactory.getInstance(authType);
        PlatformOauth platformOauth = platform.oauth(code);
        PlatformEntity platformEntity = platformDao.insertOrUpdate(platformOauth);
        PlatformUserInfo platformUserInfo = platform.userInfo(platformEntity);
        CustomersInfo customersInfo = customersService.register(platformUserInfo, platformEntity);
        return customersInfo;
    }

    public SessionInfo loginByPlatform(HttpServletResponse response, CustomersInfo customersInfo) {
        String sessionId = UUID.randomUUID().toString();
        Cookie cookie = new Cookie(BaseConstants.SESSION_COOKIE_NAME, sessionId);
        response.addCookie(cookie);
        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setCustomersId(customersInfo.getId());
        sessionInfo.setUsername(customersInfo.getUsername());
        sessionInfo.setPhone(customersInfo.getPhone());
        sessionInfo.setEmail(customersInfo.getEmail());
        sessionInfo.setPlatform(customersInfo.getPlatform());
        sessionInfo.setOpenId(customersInfo.getOpenId());
        sessionService.save(sessionId, sessionInfo);
        return sessionInfo;
    }

}
