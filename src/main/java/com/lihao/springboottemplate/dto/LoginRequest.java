package com.lihao.springboottemplate.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
    // Getters 和 Setters
    private String username;
    private String password;
    private String captcha; // 新增验证码字段

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
