package com.fhr.train.member.config;

import com.fhr.train.common.interceptor.MemberInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author FHR
 * @create 2024/12/16 14:00
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private MemberInterceptor memberInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(memberInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/member/login",
                        "/member/send/code"
                );
    }
}
