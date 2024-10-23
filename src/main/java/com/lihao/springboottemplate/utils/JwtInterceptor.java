package com.lihao.springboottemplate.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    @Autowired
    public JwtInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        String token = request.getHeader("Authorization"); // 获取请求头中的token

        if (token == null || token.isEmpty()) {
            throw new JwtAuthenticationException("缺少令牌");
        }

        try {
            Claims claims = jwtUtil.parseToken(token);
            // 验证通过，继续处理请求
        } catch (JwtException e) {
            throw new JwtAuthenticationException("令牌无效或过期");
        }

        return true; // 通过拦截器
    }
}
