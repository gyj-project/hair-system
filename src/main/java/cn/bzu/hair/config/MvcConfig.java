package cn.bzu.hair.config;

import cn.bzu.hair.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
public class MvcConfig implements WebMvcConfigurer {

    // @Bean
    public LoginInterceptor getLoginInterceptor(){
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration interceptorRegistration = registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/**");
//        interceptorRegistration.excludePathPatterns("/api/user/login");
//        interceptorRegistration.excludePathPatterns("/api/user/logout");
//        interceptorRegistration.excludePathPatterns("/api/user/find/pass");
//        interceptorRegistration.excludePathPatterns("/api/captcha/*");
//        interceptorRegistration.excludePathPatterns("/api/add/user");
//
//        WebMvcConfigurer.super.addInterceptors(registry);
    }


}
