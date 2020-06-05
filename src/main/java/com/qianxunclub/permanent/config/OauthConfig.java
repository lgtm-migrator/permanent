package com.qianxunclub.permanent.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangbin
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "config.oauth")
public class OauthConfig {

    private Qq qq;

    @Data
    public static class Qq {

        private String clientId;
        private String clientSecret;
        private String redirectUrl;
    }
}
