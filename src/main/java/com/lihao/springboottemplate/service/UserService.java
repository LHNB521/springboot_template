package com.lihao.springboottemplate.service;


import com.lihao.springboottemplate.entity.User;
import com.lihao.springboottemplate.repository.UserRepository;
import com.lihao.springboottemplate.specification.UserSpecification;
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

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Page<User> getUserList(String username, String name, String role, Integer enabled, Integer locked, Pageable pageable) {
        return userRepository.findAll(UserSpecification.filterByCriteria(username, name, role, enabled, locked), pageable);
    }

}
