package com.qianxunclub.permanent.service;

import com.qianxunclub.permanent.repository.dao.OauthDao;
import com.qianxunclub.permanent.service.auth.Auth;
import com.qianxunclub.permanent.service.auth.AuthFactory;
import com.qianxunclub.permanent.service.auth.data.OauthToken;
import com.qianxunclub.permanent.utils.JsonUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class LoginService {

    private OauthDao oauthDao;

    public String login(String platform) {
        return AuthFactory.getInstance(platform)
            .authorizeUrl("{\"platform\":\"" + platform + "\"}");
    }

    public void callback(String code, String state) {
        String authType = JsonUtil.getGson().fromJson(state, Map.class).get("platform").toString();
        Auth auth = AuthFactory.getInstance(authType);
        OauthToken token = auth.token(code);
        oauthDao.insertOrUpdate(token);
    }
}
