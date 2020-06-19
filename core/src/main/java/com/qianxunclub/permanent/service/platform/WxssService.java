package com.qianxunclub.permanent.service.platform;

import com.qianxunclub.permanent.configuration.CoreException;
import com.qianxunclub.permanent.configuration.OauthConfiguration;
import com.qianxunclub.permanent.constants.AuthConstants.WxssApi;
import com.qianxunclub.permanent.constants.BaseConstants;
import com.qianxunclub.permanent.constants.CodeConstants;
import com.qianxunclub.permanent.repository.entity.PlatformEntity;
import com.qianxunclub.permanent.service.platform.data.PlatformOauth;
import com.qianxunclub.permanent.service.platform.data.PlatformUserInfo;
import com.qianxunclub.permanent.utils.HttpUtil;
import com.qianxunclub.permanent.utils.JsonUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * @author zhangbin
 */
@Service
@AllArgsConstructor
public class WxssService extends Platform {

    private final OauthConfiguration oauthConfiguration;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public String authorizeUrl(String state) throws CoreException {
        throw CoreException.of(CodeConstants.NOT_SUPPORTED);
    }

    public PlatformOauth accessToken() {
        String wxssAccessToken = redisTemplate.opsForValue().get(BaseConstants.WXSS_ACCESS_TOKEN);
        if (StringUtils.isEmpty(wxssAccessToken)) {
            String url = String.format(
                WxssApi.AUTHORIZE,
                oauthConfiguration.getWxss().getAppId(),
                oauthConfiguration.getWxss().getAppSecret());
            wxssAccessToken = HttpUtil.httpGet(url, null);
        }
        PlatformOauth platformOauth = JsonUtil.getGson()
            .fromJson(wxssAccessToken, PlatformOauth.class);
        if (!StringUtils.isEmpty(platformOauth.getAccessToken())) {
            redisTemplate.opsForValue()
                .set(BaseConstants.WXSS_ACCESS_TOKEN, JsonUtil.getGson().toJson(platformOauth));
            redisTemplate.expire(BaseConstants.WXSS_ACCESS_TOKEN,
                Long.parseLong(platformOauth.getExpiresIn()), TimeUnit.SECONDS);
        }
        return platformOauth;
    }

    @Override
    public PlatformOauth oauth(String code) throws CoreException {
        Map<String, String> params = new HashMap<>();
        params.put("appid", oauthConfiguration.getWxss().getAppId());
        params.put("secret", oauthConfiguration.getWxss().getAppSecret());
        params.put("js_code", code);
        params.put("grant_type", "authorization_code");
        String response = HttpUtil.httpGet(WxssApi.JSCODE2SESSION, params);
        Map<String, String> map = JsonUtil.getGson().fromJson(response, Map.class);
        PlatformOauth platformOauth = new PlatformOauth();
        platformOauth.setPlatform(this.getPlatform().getPlatformName());
        platformOauth.setOpenId(map.get("openid"));
        return platformOauth;
    }

    @Override
    public PlatformUserInfo userInfo(PlatformEntity platformEntity) throws CoreException {
        throw CoreException.of(CodeConstants.NOT_SUPPORTED);
    }
}
