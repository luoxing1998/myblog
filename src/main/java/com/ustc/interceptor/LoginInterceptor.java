package com.ustc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author luoxing
 * @create 2021-03-24 21:31
 * 登录过滤器
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if(request.getSession().getAttribute("user") == null){
            response.sendRedirect("/admin");//未登录重定向到登录页面
            return false;
        }
        return true;
    }
}
