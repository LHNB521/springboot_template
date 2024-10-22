package com.lihao.springboottemplate.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        // 创建CORS配置
        CorsConfiguration config = new CorsConfiguration();

        // 允许所有IP地址但仅限端口为1209的请求
        config.addAllowedOriginPattern("http://*:1209");
        config.addAllowedOriginPattern("https://*:1209");


        // 是否发送 Cookie 信息
        config.setAllowCredentials(true);

        // 允许的请求方法，* 表示允许所有方法
        config.addAllowedMethod("*");

        // 允许的请求头
        config.addAllowedHeader("*");

        // 创建基于路径的 CORS 配置源
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // 将配置应用于所有路径
        source.registerCorsConfiguration("/**", config);

        // 返回一个新的 CorsFilter 实例
        return new CorsFilter(source);
    }
}
