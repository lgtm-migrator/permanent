package com.qianxunclub.permanent.service;

import com.qianxunclub.permanent.service.auth.Auth;
import com.qianxunclub.permanent.service.auth.AuthFactory;
import com.qianxunclub.permanent.utils.JsonUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginService {

    public String login(String authType) {
        return AuthFactory.getInstance(authType).authorizeUrl("{\"authType\":\"" + authType + "\"}");
    }

    public void callback(String code, String state) {
        String authType = JsonUtil.getGson().fromJson(state, Map.class).get("authType").toString();
        Auth auth = AuthFactory.getInstance(authType);
        String openId = auth.openId(code);
        System.out.println("openId:" + openId);
    }
}
