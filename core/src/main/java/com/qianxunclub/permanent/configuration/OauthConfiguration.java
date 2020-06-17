package com.qianxunclub.permanent.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangbin
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "config.oauth")
public class OauthConfiguration {

    private Qq qq;
    private Wxss wxss;

    @Data
    public static class Qq {

        private String clientId;
        private String clientSecret;
        private String redirectUrl;
    }

    @Data
    public static class Wxss {

        private String appId;
        private String appSecret;
    }
}
