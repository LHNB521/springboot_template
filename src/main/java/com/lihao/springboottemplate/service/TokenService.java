package com.lihao.springboottemplate.service;

import com.lihao.springboottemplate.entity.TokenEntity;
import com.lihao.springboottemplate.repository.TokenRepository;
import com.lihao.springboottemplate.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public TokenService(TokenRepository tokenRepository, JwtUtil jwtUtil) {
        this.tokenRepository = tokenRepository;
        this.jwtUtil = jwtUtil;
    }

    // 保存 token 到数据库
    public String saveOrUpdateToken(String username) {

        // 1. 生成 token
        String token = jwtUtil.generateToken(username);

        // 2. 设置 token 过期时间（例如 1小时后过期）
        LocalDateTime expireAt = LocalDateTime.now().plusHours(1);

        // 2. 创建或更新 TokenEntity
        TokenEntity tokenEntity = tokenRepository.findByUsername(username)
                .orElse(new TokenEntity(username, token, expireAt));
        tokenEntity.setToken(token);
        tokenEntity.setExpireAt(expireAt);

        // 3. 保存或更新 token 到数据库
        tokenRepository.save(tokenEntity);

        return token;
    }

    // 验证 token 的逻辑
    public boolean validateToken(String token) {
        Optional<TokenEntity> tokenOptional = tokenRepository.findByToken(token);
        return tokenOptional.isPresent();
    }

    // 删除 Token
    public void deleteToken(String token) {
        tokenRepository.deleteByToken(token);
    }

    // 根据用户名查找 token
    public String getTokenByUsername(String username) {
        return tokenRepository.findByUsername(username)
                .map(TokenEntity::getToken) // 获取 token 字符串
                .orElse(null); // 如果没有找到，返回 null
    }
}
