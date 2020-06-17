package com.qianxunclub.permanent.controller;


import com.qianxunclub.permanent.configuration.CoreException;
import com.qianxunclub.permanent.model.CustomersInfo;
import com.qianxunclub.permanent.model.Result;
import com.qianxunclub.permanent.service.AuthService;

import com.qianxunclub.permanent.service.platform.data.PlatformOauth;
import com.qianxunclub.permanent.service.platform.data.PlatformUserInfo;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangbin
 */
@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("authorize/{platform}")
    public void authorize(
        @PathVariable String platform,
        HttpServletResponse response
    ) throws CoreException, IOException {
        response.sendRedirect(authService.authorize(platform));
    }

    @PostMapping("login/{platform}")
    public void login(
        @PathVariable String platform,
        HttpServletResponse response,
        HttpServletRequest request,
        @RequestBody PlatformUserInfo platformUserInfo,
        @RequestBody PlatformOauth platformOauth
    ) {
        CustomersInfo customersInfo = authService.register(platformOauth, platformUserInfo);
        authService.loginByPlatform(request, response, customersInfo);
    }

    @GetMapping("callback")
    @ResponseBody
    public Result callback(
        HttpServletResponse response,
        HttpServletRequest request,
        @RequestParam String code,
        @RequestParam String state
    ) throws CoreException {
        CustomersInfo customersInfo = authService.callback(code, state);
        return Result.success(authService.loginByPlatform(request, response, customersInfo));
    }
}
