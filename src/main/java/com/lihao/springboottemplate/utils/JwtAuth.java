package com.lihao.springboottemplate.utils;

import java.lang.annotation.*;

@Target(ElementType.METHOD) // 注解用于方法
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JwtAuth {
}
