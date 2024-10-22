package com.lihao.springboottemplate.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEnabled() {
        return this.enabled;
    }

    public void setEnabled(int i) {
        this.enabled = i;
    }

    public Integer getLocked() {
        return this.locked;
    }
}