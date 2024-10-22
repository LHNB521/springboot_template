package com.lihao.springboottemplate.controller;


import com.lihao.springboottemplate.dto.LoginRequest;
import com.lihao.springboottemplate.service.AuthService;
import com.lihao.springboottemplate.utils.ApiResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    // 构造函数注入
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // 登录接口
    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody LoginRequest loginRequest, HttpSession session) {

        // 验证验证码
        String sessionCaptcha = (String) session.getAttribute("captcha");
        System.out.println(sessionCaptcha);
        System.out.println(loginRequest.getCaptcha());
        if (sessionCaptcha == null || !sessionCaptcha.equals(loginRequest.getCaptcha())) {
            return new ApiResponse<>(400, "验证码无效", null);
        }
        return authService.login(loginRequest);
    }

}
