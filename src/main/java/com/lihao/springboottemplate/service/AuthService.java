package com.lihao.springboottemplate.service;


import com.lihao.springboottemplate.config.CustomException;
import com.lihao.springboottemplate.dto.LoginRequest;
import com.lihao.springboottemplate.dto.RegisterRequest;
import com.lihao.springboottemplate.entity.UserEntity;
import com.lihao.springboottemplate.repository.UserRepository;
import com.lihao.springboottemplate.utils.ApiResponse;
import com.lihao.springboottemplate.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    // 使用构造函数注入
    @Autowired
    public AuthService(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    // 登录逻辑
    public ApiResponse<String> login(LoginRequest loginRequest) {
        // 1. 查找用户是否存在
        UserEntity user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new CustomException(404, "用户不存在"));

        if (user.getLocked() == 1) {
            throw new CustomException(400, "用户已锁定");
        }
        if (user.getEnabled() == 0) {
            throw new CustomException(400, "用户未激活");
        }
        // 2. 验证密码
        if (!PasswordUtil.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new CustomException(400, "用户名或密码错误");
        }

        // 3. 将 token 存储到数据库
        String token = tokenService.saveOrUpdateToken(user.getUsername());

        // 5. 如果验证通过，返回成功响应 (可以返回 JWT 或其他 Token)
        return ApiResponse.success(token);
    }

    // 注册逻辑
    public ApiResponse<String> register(RegisterRequest registerRequest) {
        // 验证用户名是否已经存在
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return new ApiResponse<>(400, "用户已存在", null);
        }

        // 创建新用户
        UserEntity newUser = new UserEntity();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setPassword(PasswordUtil.encodePassword(registerRequest.getPassword()));
        newUser.setName(registerRequest.getName());
        newUser.setEnabled(0);

        // 保存用户信息到数据库
        userRepository.save(newUser);

        // 返回注册成功信息
        return new ApiResponse<>(200, "注册成功", null);
    }
}
