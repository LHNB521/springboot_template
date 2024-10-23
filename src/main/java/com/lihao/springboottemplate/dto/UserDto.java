package com.lihao.springboottemplate.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    private String username;
    private String name;
    private String role;
    private Integer enabled;
    private Integer locked;

    // 无参构造函数
    public UserDto() {
    }

    public Integer isEnabled() {
        return enabled;
    }

    public Integer isLocked() {
        return locked;
    }
}
