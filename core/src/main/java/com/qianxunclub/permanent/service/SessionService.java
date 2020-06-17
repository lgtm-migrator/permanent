package com.qianxunclub.permanent.service;

import com.qianxunclub.permanent.configuration.SessionConfiguration;
import com.qianxunclub.permanent.model.SessionInfo;
import com.qianxunclub.permanent.utils.JsonUtil;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author zhangbin
 */
@Service
@AllArgsConstructor
public class SessionService {

    private final RedisTemplate<String, String> redisTemplate;
    private final SessionConfiguration sessionConfiguration;

    public void save(String sessionId, SessionInfo sessionInfo) {
        if (Boolean.FALSE.equals(redisTemplate.hasKey(sessionId))) {
            String session = JsonUtil.getGson().toJson(sessionInfo);
            redisTemplate.opsForValue().set(sessionId, session);
        }
        redisTemplate.expire(sessionId, sessionConfiguration.getTimeout(), TimeUnit.HOURS);
    }

    public SessionInfo get(String sessionId) {
        return JsonUtil.getGson()
            .fromJson(redisTemplate.opsForValue().get(sessionId), SessionInfo.class);
    }

}
