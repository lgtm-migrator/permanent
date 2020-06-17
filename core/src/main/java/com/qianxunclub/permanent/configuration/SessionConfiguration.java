package com.qianxunclub.permanent.configuration;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangbin
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "config.session")
public class SessionConfiguration {

    private Integer timeout = 24;


}
