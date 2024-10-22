package com.lihao.springboottemplate.controller;

import com.lihao.springboottemplate.entity.User;
import com.lihao.springboottemplate.service.UserService;
import com.lihao.springboottemplate.utils.ApiResponse;
import com.lihao.springboottemplate.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/currentUser")
    public ApiResponse<Optional<User>> getCurrentUser(@RequestHeader("Authorization") String token) {
        try {
            // 解析JWT Token，获取Claims
            Claims claims = JwtUtil.parseToken(token);
            // 从Claims中获取用户名
            String currentUsername = claims.getSubject();
            // 返回用户信息
            return userService.findByUsername(currentUsername);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SecurityException e) {
            // Token 解析失败处理
            return ApiResponse.error(401, "Token 无效或已过期");
        } catch (Exception e) {
            return ApiResponse.error(400, "获取用户信息失败");
        }
    }
}
