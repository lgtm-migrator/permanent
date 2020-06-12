package com.qianxunclub.permanent.controller;


import com.qianxunclub.permanent.model.CustomersInfo;
import com.qianxunclub.permanent.model.Result;
import com.qianxunclub.permanent.service.AuthService;

import lombok.AllArgsConstructor;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public void auth(
        @RequestParam String platform,
        HttpServletResponse response
    ) throws IOException {
        response.sendRedirect(authService.auth(platform));
    }

    @GetMapping("callback")
    @ResponseBody
    public Result callback(
        HttpServletResponse response,
        @RequestParam String code,
        @RequestParam String state
    ) {
        CustomersInfo customersInfo = authService.callback(code, state);
        authService.loginByPlatform(response, customersInfo);
        return null;
    }
}
