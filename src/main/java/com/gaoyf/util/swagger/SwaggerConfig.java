package com.gaoyf.util.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by 高宇飞 on 2018/9/27 16:42:05
 * swagger配置类
 */
@EnableWebMvc
@EnableSwagger2
@Configuration
@PropertySource("classpath:application.properties")
public class SwaggerConfig {

    //swagger title
    @Value("${swagger.title}")
    private String SWAGGER_TITLE;
    //swagger description
    @Value("${swagger.description}")
    private String SWAGGER_DESCRIPTION;
    //swagger termsOfServiceUrl
    @Value("${swagger.termsOfServiceUrl}")
    private String SWAGGER_TERM;
    //swagger contact
    @Value("${swagger.contact}")
    private String SWAGGER_CONTACT;
    //swagger version
    @Value("${swagger.version}")
    private String SWAGGER_VERSION;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("PublicSecurity api")
                .apiInfo(apiInfo())
                .select()
//                .apis(RequestHandlerSelectors.any())    //所有API
                .apis(RequestHandlerSelectors.basePackage("com.gaoyf.jpa")) // 注意修改此处的包名
                .paths(PathSelectors.any()) //
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(SWAGGER_TITLE) // 任意，请稍微规范点
                .description(SWAGGER_DESCRIPTION) // 任意，请稍微规范点
                .termsOfServiceUrl(SWAGGER_TERM) // 将“url”换成自己的ip:port
                .version(SWAGGER_VERSION)
                .build();
    }

}
