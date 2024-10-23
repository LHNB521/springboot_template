package com.lihao.springboottemplate.repository;


import com.lihao.springboottemplate.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    // 根据用户名查找 token
    Optional<TokenEntity> findByUsername(String username); // 这里用 username 来查找

    Optional<TokenEntity> findByToken(String token);

    void deleteByToken(String token);
}
