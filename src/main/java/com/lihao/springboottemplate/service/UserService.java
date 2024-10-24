package com.lihao.springboottemplate.service;


import com.lihao.springboottemplate.dto.UserDto;
import com.lihao.springboottemplate.entity.UserEntity;
import com.lihao.springboottemplate.repository.UserRepository;
import com.lihao.springboottemplate.specification.UserSpecification;
import com.lihao.springboottemplate.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Page<UserEntity> getUserList(String username, String name, Integer role, Integer enabled, Integer locked, Pageable pageable) {
        return userRepository.findAll(UserSpecification.filterByCriteria(username, name, role, enabled, locked), pageable);
    }

    public ApiResponse<String> updateUser(UserDto user) {
        // 验证用户名是否已经存在
        Optional<UserEntity> existingUserOpt = userRepository.findByUsername(user.getUsername());

        if (existingUserOpt.isEmpty()) {
            return new ApiResponse<>(400, "用户不存在", null);
        }

        UserEntity existingUser = existingUserOpt.get();

        // 根据是否存在值来更新用户信息
        if (user.getName() != null) {
            existingUser.setName(user.getName());
        }
        if (user.getRole() != null) {
            existingUser.setRole(user.getRole());
        }
        if (user.getEnabled() != null) {
            existingUser.setEnabled(user.getEnabled());
        }
        if (user.getLocked() != null) {
            existingUser.setLocked(user.getLocked());
        }
        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }
        // 可以根据需要更新其他字段

        // 保存用户信息到数据库
        userRepository.save(existingUser);

        return new ApiResponse<>(200, "更新成功", null);
    }


}
