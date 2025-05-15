package com.example.apipassenger.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: InterceptorConfig
 * Package: com.example.apipassenger.interceptor
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/15 10:35
 * @Version 1.0
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                // 拦截路径
                .addPathPatterns("/**")
                // 不拦截路径
                .excludePathPatterns("/verification-code","/verification-code-check");
    }
}
