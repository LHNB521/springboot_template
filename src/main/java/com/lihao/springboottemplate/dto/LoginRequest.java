package com.lihao.springboottemplate.dto;

public class LoginRequest {
    private String username;
    private String password;
    private String captcha; // 新增验证码字段

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters 和 Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }
}
