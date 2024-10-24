package com.lihao.springboottemplate.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    private String username;
    private String name;
    private Integer role;
    private Integer enabled;
    private Integer locked;
    private String email;

    // 无参构造函数
    public UserDto() {
    }
}
