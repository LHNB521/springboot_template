package com.lihao.springboottemplate.repository;

import com.lihao.springboottemplate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 根据用户名查找用户
    Optional<User> findByUsername(String username);

    // 自定义方法，Spring Data JPA 会自动实现
//    boolean existsByUsername(String username);

}