package com.lihao.springboottemplate.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class JwtAuthAspect {
    private final HttpServletRequest request;

    public JwtAuthAspect(HttpServletRequest request) {
        this.request = request;
    }

    @Before("@annotation(com.lihao.springboottemplate.utils.JwtAuth)")
    public void checkToken() {
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Missing token");
        }

        try {
            JwtUtil.parseToken(token);
        } catch (Exception e) {
            throw new RuntimeException("Invalid token");
        }
    }
}
