package com.lihao.springboottemplate.service;

import com.lihao.springboottemplate.entity.TokenEntity;
import com.lihao.springboottemplate.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    @Autowired
    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    // 保存 token 到数据库
    public void saveOrUpdateToken(String username, String token, LocalDateTime expireAt) {

        // 创建或更新 TokenEntity
        TokenEntity tokenEntity = tokenRepository.findByUsername(username)
                .orElse(new TokenEntity(username, token, expireAt));

        tokenEntity.setToken(token);
        tokenEntity.setExpireAt(expireAt);

        // 保存或更新 token 到数据库
        tokenRepository.save(tokenEntity);
    }

    // 根据用户名查找 token
    public String getTokenByUsername(String username) {
        return tokenRepository.findByUsername(username)
                .map(TokenEntity::getToken) // 获取 token 字符串
                .orElse(null); // 如果没有找到，返回 null
    }
}
