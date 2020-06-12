package com.qianxunclub.permanent.interceptor;

import com.qianxunclub.permanent.configuration.CoreException;
import com.qianxunclub.permanent.constants.BaseConstants;
import com.qianxunclub.permanent.constants.CodeConstants;
import com.qianxunclub.permanent.model.SessionInfo;
import com.qianxunclub.permanent.service.SessionService;
import com.qianxunclub.permanent.utils.CookieUtil;
import javax.servlet.http.Cookie;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangbin
 */
@Slf4j
@Component
@AllArgsConstructor
public class ProtectedInterceptor extends HandlerInterceptorAdapter {

    private SessionService sessionService;

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws Exception {
        Cookie cookie = CookieUtil.get(request, BaseConstants.SESSION_COOKIE_NAME);
        if (cookie == null) {
            throw CoreException.of(CodeConstants.USER_NOT_LOGIN);
        }
        String sessionId = cookie.getValue();
        SessionInfo sessionInfo = sessionService.get(sessionId);
        if (sessionInfo == null) {
            throw CoreException.of(CodeConstants.USER_NOT_LOGIN);
        }
        sessionService.save(sessionId, sessionInfo);
        return super.preHandle(request, response, handler);
    }
}
