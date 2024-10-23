package com.lihao.springboottemplate.controller;

import com.lihao.springboottemplate.dto.LoginRequest;
import com.lihao.springboottemplate.dto.RegisterRequest;
import com.lihao.springboottemplate.service.AuthService;
import com.lihao.springboottemplate.service.CaptchaService;
import com.lihao.springboottemplate.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final CaptchaService captchaService;


    // 构造函数注入
    @Autowired
    public AuthController(AuthService authService, CaptchaService captchaService) {
        this.authService = authService;
        this.captchaService = captchaService;
    }

    // 登录接口
    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody LoginRequest loginRequest) {

        // 验证验证码
//        if (!captchaService.validateCaptcha(loginRequest.getCaptcha())) {
//            return new ApiResponse<>(400, "验证码无效", null);
//        }
        return authService.login(loginRequest);
    }

    // 注册接口
    @PostMapping("/register")
    public ApiResponse<String> register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    // 验证码
    @GetMapping("/captcha")
    public ApiResponse<String> getCaptcha() {
        String captcha = captchaService.generateCaptcha();
        ;
        return ApiResponse.success(captcha);
    }
}
