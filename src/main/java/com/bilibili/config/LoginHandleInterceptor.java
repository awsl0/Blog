package com.bilibili.config;

import com.bilibili.pojo.User;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandleInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        if (user==null) {
            request.setAttribute("message", "没有权限，请先登录");
            request.getRequestDispatcher("/admin").forward(request, response);
            return false;
        } else {
            return true;
        }
    }
}
