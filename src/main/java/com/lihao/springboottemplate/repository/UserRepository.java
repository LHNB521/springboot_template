package com.lihao.springboottemplate.repository;

import com.lihao.springboottemplate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    // 根据用户名查找用户
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}