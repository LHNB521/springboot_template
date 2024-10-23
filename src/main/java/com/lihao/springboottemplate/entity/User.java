package com.lihao.springboottemplate.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String name;

    @Column()
    private String role;

    @Column(nullable = false)
    private Integer enabled = 0;  // 账户是否激活

    @Column()
    private Integer locked = 0;  // 账户是否锁定

    // 可选：添加创建时间和更新时间
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdAt = new java.util.Date();

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date updatedAt = new java.util.Date();

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new java.util.Date();
    }
}