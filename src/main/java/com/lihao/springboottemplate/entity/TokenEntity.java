package com.lihao.springboottemplate.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_tokens")
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime expire_at;

    // 构造函数
    public TokenEntity() {
    }

    public TokenEntity(String username, String token, LocalDateTime expireAt) {
        this.username = username;
        this.token = token;
        this.expire_at = expireAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpireAt() {
        return expire_at;
    }

    public void setExpireAt(LocalDateTime expire_at) {
        this.expire_at = expire_at;
    }
}
