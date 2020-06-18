package com.qianxunclub.permanent.service;

import com.qianxunclub.permanent.configuration.CoreException;
import com.qianxunclub.permanent.constants.BaseConstants;
import com.qianxunclub.permanent.constants.CodeConstants;
import com.qianxunclub.permanent.constants.PlatformConstants;
import com.qianxunclub.permanent.model.CustomersInfo;
import com.qianxunclub.permanent.model.SessionInfo;
import com.qianxunclub.permanent.repository.dao.CustomersBindingDao;
import com.qianxunclub.permanent.repository.dao.PlatformDao;
import com.qianxunclub.permanent.repository.entity.PlatformEntity;
import com.qianxunclub.permanent.service.platform.Platform;
import com.qianxunclub.permanent.service.platform.PlatformFactory;
import com.qianxunclub.permanent.service.platform.data.PlatformOauth;
import com.qianxunclub.permanent.service.platform.data.PlatformUserInfo;
import com.qianxunclub.permanent.utils.CookieUtil;
import com.qianxunclub.permanent.utils.JsonUtil;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhangbin
 */
@Service
@AllArgsConstructor
public class AuthService {

    private final PlatformDao platformDao;
    private final CustomersService customersService;
    private final SessionService sessionService;
    private final CustomersBindingDao customersBindingDao;

    public String authorize(String platform) throws CoreException {
        if (PlatformConstants.WXSS.equals(PlatformConstants.valueOf(platform))) {
            return null;
        }
        return PlatformFactory.getInstance(platform)
            .authorizeUrl("{\"platform\":\"" + platform + "\"}");
    }

    @Transactional(rollbackFor = {Exception.class})
    public CustomersInfo callback(String code, String state) throws CoreException {
        String authType = JsonUtil.getGson().fromJson(state, Map.class).get("platform").toString();
        Platform platform = PlatformFactory.getInstance(authType);
        PlatformOauth platformOauth = platform.oauth(code);
        PlatformEntity platformEntity = platformDao.insertOrUpdate(platformOauth);
        PlatformUserInfo platformUserInfo = platform.userInfo(platformEntity);
        return customersService.register(platformUserInfo, platformEntity);
    }

    public CustomersInfo registerByWxss(String code, PlatformUserInfo platformUserInfo)
        throws CoreException {
        if (platformUserInfo.getNickname() == null
            || platformUserInfo.getGender() == null
            || platformUserInfo.getAvatarUrl() == null
        ) {
            throw CoreException.of(CodeConstants.PARAMETER_INVALID);
        }
        Platform platform = PlatformFactory.getInstance(PlatformConstants.WXSS);
        PlatformOauth platformOauth = platform.oauth(code);
        if (platformOauth.getOpenId() == null) {
            throw CoreException.of(CodeConstants.FAIL);
        }
        PlatformEntity platformEntity = platformDao.insertOrUpdate(platformOauth);
        return customersService.register(platformUserInfo, platformEntity);
    }

    public SessionInfo loginByPlatform(
        HttpServletRequest request,
        HttpServletResponse response,
        CustomersInfo customersInfo
    ) throws CoreException {
        if (null == customersInfo.getId()) {
            throw CoreException
                .of(CodeConstants.PARAMETER_INVALID.setMessage("customersInfo not null"));
        }
        String sessionId = null;
        SessionInfo sessionInfo = null;
        Cookie cookie = CookieUtil.get(request, BaseConstants.SESSION_COOKIE_NAME);
        if (cookie != null) {
            sessionId = cookie.getValue();
            SessionInfo si = sessionService.get(sessionId);
            if (si != null) {
                sessionInfo = si;
            }
        }
        if (sessionInfo == null) {
            sessionId = UUID.randomUUID().toString();
            cookie = new Cookie(BaseConstants.SESSION_COOKIE_NAME, sessionId);
            response.addCookie(cookie);
            sessionInfo = new SessionInfo();
            sessionInfo.setCustomersId(customersInfo.getId());
            sessionInfo.setUsername(customersInfo.getUsername());
            sessionInfo.setNickname(customersInfo.getNickname());
            sessionInfo.setGender(customersInfo.getGender());
            sessionInfo.setPhone(customersInfo.getPhone());
            sessionInfo.setEmail(customersInfo.getEmail());
            sessionInfo.setPlatform(customersInfo.getPlatform());
            sessionInfo.setOpenId(customersInfo.getOpenId());
        }
        sessionService.save(sessionId, sessionInfo);
        return sessionInfo;
    }

}
