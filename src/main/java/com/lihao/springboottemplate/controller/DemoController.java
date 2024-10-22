package com.lihao.springboottemplate.controller;

import com.lihao.springboottemplate.config.CustomException;
import com.lihao.springboottemplate.utils.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    //    @JwtAuth
    @GetMapping("/demo")
    public ApiResponse<String> demo(@RequestParam(value = "error", required = false) String error) {
        if ("true".equals(error)) {
            throw new CustomException(400, "这是一个自定义异常");
        }
        return ApiResponse.success("正常返回的数据");
    }
//
//    @GetMapping("/errorDemo")
//    public ApiResponse<String> getErrorDemo() {
//        return ApiResponse.error(400, "Bad Request Example");
//    }

}
