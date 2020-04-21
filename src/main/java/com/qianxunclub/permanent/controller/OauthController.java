package com.qianxunclub.permanent.controller;


import com.qianxunclub.permanent.service.LoginService;
import com.qianxunclub.permanent.service.oauth.QqService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("oauth")
@AllArgsConstructor
public class OauthController {

    private QqService qqService;
    private LoginService loginService;

    @GetMapping
    public void callback(
            @RequestParam String code,
            @RequestParam String state
    ) {
        String openId = qqService.callback(code, state);
    }
}
