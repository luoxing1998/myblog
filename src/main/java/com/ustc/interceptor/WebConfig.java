package com.ustc.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author luoxing
 * @create 2021-03-24 21:35
 * 拦截配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")//所有/admin/下的都拦截
                .excludePathPatterns("/admin") //排除掉登录请求
                .excludePathPatterns("/admin/login");
    }
}
