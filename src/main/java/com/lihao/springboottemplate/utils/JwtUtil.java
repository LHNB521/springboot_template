package com.lihao.springboottemplate.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtUtil {

    // 生成安全的密钥对象
    private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);  // HS512 加密算法

    // 验证并解析JWT Token
    public static Claims parseToken(String token) {
        System.out.println(key);
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
        System.out.println(key);
        // 过期时间，1天
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expirationDate)
                .signWith(key)  // 使用 SecretKey
                .compact();
    }

}
