package com.lihao.springboottemplate.utils;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // 生成安全的密钥对象
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);  // HS512 加密算法

    // 验证并解析JWT Token
    public static Claims parseToken(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SecurityException, IllegalArgumentException {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY) // 设置签名密钥
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 生成 Token
    public String generateToken(String username) {
        // 过期时间，1天
        long expiration = 86400000L;
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SECRET_KEY)  // 使用 SecretKey
                .compact();
    }

}
