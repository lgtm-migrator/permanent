package com.qianxunclub.permanent.controller;


import com.qianxunclub.permanent.service.LoginService;
import com.qianxunclub.permanent.utils.HttpUtil;

import lombok.AllArgsConstructor;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthController {

    private LoginService loginService;

    @GetMapping("login")
    public void login(@RequestParam String authType,HttpServletResponse response
    ) {
    	try {
			response.sendRedirect(loginService.login(authType));
		} catch (IOException e) {
			e.printStackTrace();
		} 
    }

    @GetMapping
    public void callback(
            @RequestParam String code,
            @RequestParam String state
    ) {
        loginService.callback(code, state);
    }
}
