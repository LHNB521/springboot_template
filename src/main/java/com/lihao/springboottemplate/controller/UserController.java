package com.lihao.springboottemplate.controller;

import com.lihao.springboottemplate.dto.UserDto;
import com.lihao.springboottemplate.entity.UserEntity;
import com.lihao.springboottemplate.service.UserService;
import com.lihao.springboottemplate.utils.ApiResponse;
import com.lihao.springboottemplate.utils.JwtUtil;
import com.lihao.springboottemplate.utils.PagedResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/currentUser")
    public ApiResponse<Optional<UserEntity>> getCurrentUser(@RequestHeader("Authorization") String token) {
        try {
            // 解析JWT Token，获取Claims
            Claims claims = jwtUtil.parseToken(token);
            // 从Claims中获取用户名
            String currentUsername = claims.getSubject();
            // 返回用户信息
            Optional<UserEntity> user = userService.findByUsername(currentUsername);
            return ApiResponse.success(user);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SecurityException e) {
            // Token 解析失败处理
            return ApiResponse.error(401, "Token 无效或已过期");
        } catch (Exception e) {
            return ApiResponse.error(400, "获取用户信息失败");
        }
    }

    //获取所有用户信息
    @GetMapping("/getUserList")
    public ApiResponse<PagedResponse<UserEntity>> getUserList(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, UserDto filterCriteria) {
        Page<UserEntity> userPage = userService.getUserList(
                filterCriteria.getUsername(),
                filterCriteria.getName(),
                filterCriteria.getRole(),
                filterCriteria.getEnabled(),
                filterCriteria.getLocked(),
                PageRequest.of(page, size));
        PagedResponse<UserEntity> pagedResponse = new PagedResponse<>(userPage.getContent(), userPage.getTotalElements());
        return ApiResponse.success(pagedResponse);
    }

    // 添加用户
    @PostMapping("/addUser")
    public ApiResponse<String> addUser(@RequestBody UserDto user) {
        return userService.addUser(user);
    }

    // 更新用户
    @PostMapping("/updateUser")
    public ApiResponse<String> updateUser(@RequestBody UserDto user) {
        return userService.updateUser(user);
    }

    // 删除用户
    @PostMapping("/deleteUser")
    public ApiResponse<String> deleteUser(@RequestBody UserDto user) {
        return userService.deleteUser(user);
    }

}
