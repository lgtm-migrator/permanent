package com.qianxunclub.permanent.configuration;

import static com.google.common.base.Predicates.not;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhangbin
 */
@Data
@EnableSwagger2
@Configuration
@ConfigurationProperties(prefix = "swagger")
public class SwaggerConfiguration {

    private Boolean enabled;
    private String title;
    private String description;
    private String webBasePackage;
    private String author;
    private String url;
    private String email;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .enable(this.getEnabled())
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage(this.getWebBasePackage()))
            .paths(not(regex("/error.*")))
            .build()
            .securitySchemes(securitySchemes())
            .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title(this.getTitle())
            .description(this.getDescription())
            .contact(new Contact(this.getAuthor(), this.getUrl(), this.getEmail()))
            .build();
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList = new ArrayList();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", "header"));
        return apiKeyList;
    }

    private List<SecurityContext> securityContexts() {
        SecurityContext securityContext = SecurityContext.builder()
            .securityReferences(defaultAuth())
            .build();
        List<SecurityContext> securityContextList = new ArrayList<>();
        securityContextList.add(securityContext);
        return securityContextList;
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global",
            "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        SecurityReference securityReference = new SecurityReference("Authorization",
            authorizationScopes);
        List<SecurityReference> securityReferenceList = new ArrayList<>();
        securityReferenceList.add(securityReference);
        return securityReferenceList;
    }

}
