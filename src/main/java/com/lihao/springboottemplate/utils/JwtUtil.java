package com.lihao.springboottemplate.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")  // 从配置文件中读取密钥
    private String secretKey;

    private SecretKey key;

    // 初始化时将字符串密钥转换为SecretKey
    @PostConstruct
    public void init() {
        // 检查密钥长度
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        if (keyBytes.length < 32) {
            // 密钥不足，生成一个新的密钥
            this.key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        } else {
            // 使用自定义密钥
            this.key = Keys.hmacShaKeyFor(keyBytes);
        }
    }


    // 验证并解析JWT Token
    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key) // 设置签名密钥
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 生成 Token
    public String generateToken(String username) {
        LocalDateTime expirationTime = LocalDateTime.now().plusHours(1);
        Date expirationDate = Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant());
        // 过期时间，1天
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expirationDate)
                .signWith(key)  // 使用 SecretKey
                .compact();
    }

}
