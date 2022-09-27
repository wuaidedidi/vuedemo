package com.lichen.lichenblog.admin.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lichen
 * @version 1.0.0
 * @className WebMvcConfigurer.java
 * @Description TODO 跨域配置 通常是靠nginx来解决
 * @createTime 2022年09月13日 17:09:00
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedOriginPatterns("*")
                .allowedMethods("*");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //映射 imageFiles  和e:/test
        registry.addResourceHandler("/imageFiles/**").addResourceLocations("file:/E:/test/");
    }


}
