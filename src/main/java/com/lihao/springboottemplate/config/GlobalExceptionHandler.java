package com.lihao.springboottemplate.config;

import com.lihao.springboottemplate.utils.ApiResponse;
import com.lihao.springboottemplate.utils.JwtAuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice  // 全局捕获 RestController 异常
public class GlobalExceptionHandler {

    // 捕获通用异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<String> handleGeneralException(Exception ex) {
        ex.printStackTrace();  // 打印异常信息，便于调试
        return ApiResponse.error(500, "服务器内部错误");
    }

    // 捕获自定义异常
    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<String> handleCustomException(CustomException ex) {
        return ApiResponse.error(ex.getCode(), ex.getMessage());
    }

    // 捕获其他异常，如非法参数异常
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ApiResponse.error(400, "非法参数: " + ex.getMessage());
    }

    // 处理JWT相关的异常
    @ExceptionHandler(JwtAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 设置401状态码
    public ApiResponse<String> handleJwtAuthenticationException(JwtAuthenticationException ex) {
        return ApiResponse.error(401, ex.getMessage());
    }

    // 你可以根据需要添加更多的异常处理
}
