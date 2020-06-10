package com.qianxunclub.permanent.configuration;

import com.qianxunclub.permanent.interceptor.ProtectedInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhangbin
 */
@Configuration
@AllArgsConstructor
public class WebAdapter implements WebMvcConfigurer {

    private final ProtectedInterceptor protectedInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(protectedInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns("/api/auth/**");
    }

}
