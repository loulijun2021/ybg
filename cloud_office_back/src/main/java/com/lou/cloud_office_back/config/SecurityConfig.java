//package com.lou.cloud_office_back.config;
//
//import com.lou.cloud_office_back.common.TokenInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @Author llj
// * @Date 2022/1/19 9:46
// * @Version 1.0
// */
//@Configuration
//public class SecurityConfig implements WebMvcConfigurer {
//
//    @Autowired
//    private TokenInterceptor tokenInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(tokenInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/user/**");
//    }
//}
