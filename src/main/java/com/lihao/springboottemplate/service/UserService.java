package com.lihao.springboottemplate.service;


import com.lihao.springboottemplate.entity.User;
import com.lihao.springboottemplate.repository.UserRepository;
import com.lihao.springboottemplate.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ApiResponse<Optional<User>> findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return ApiResponse.success(user);
    }

}
