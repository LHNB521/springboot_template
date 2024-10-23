/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : lg_archives

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 23/10/2024 17:28:16
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_tokens
-- ----------------------------
DROP TABLE IF EXISTS `user_tokens`;
CREATE TABLE `user_tokens`
(
    `id`        bigint                                                        NOT NULL AUTO_INCREMENT,
    `username`  varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
    `token`     varchar(512) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
    `expire_at` timestamp                                                     NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_tokens
-- ----------------------------
INSERT INTO `user_tokens`
VALUES (1, 'admin',
        'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTcyOTY3MDQ3NH0.z-1n7NMj5bEcEoWdPeZ3tPzJ4h1g2Ln7q5GkN6JSyvEPm4rS79uzb92CP0yPD4OLyPJ_Yggjieiv_M99HgTzpg',
        '2024-10-23 16:01:14');
INSERT INTO `user_tokens`
VALUES (2, 'admin1',
        'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbjEiLCJleHAiOjE3Mjk2NzgxMjN9.V5Sor9PRon8QGoWUi_XzKePpNfwxeRbgaCeMw918VDA',
        '2024-10-23 18:08:44');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `id`         bigint                                                        NOT NULL AUTO_INCREMENT,
    `username`   varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NOT NULL,
    `email`      varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
    `password`   varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
    `name`       varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
    `role`       bigint NULL DEFAULT NULL,
    `enabled`    tinyint(1) NULL DEFAULT 1 COMMENT '账户是否激活',
    `locked`     tinyint(1) NULL DEFAULT 0 COMMENT '账户是否锁定',
    `created_at` timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `username`(`username` ASC) USING BTREE,
    UNIQUE INDEX `email`(`email` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users`
VALUES (1, 'admin', 'xxxx', '$2a$10$TmO.vTtE9y/xR4pPx08ju.jjPZcNao9G2qvD4PXNHENoHdAZUi87e', '李艮', 1, 1, 0,
        '2024-10-21 16:41:25', '2024-10-22 10:30:14');
INSERT INTO `users`
VALUES (2, 'admin1', NULL, '$2a$10$V9.Ki6YFv0LKHy9G/MQqOu3JTyt/idBZpIW5oxlgq0ijjRRTW.mf2', '李浩', NULL, 1, 0,
        '2024-10-22 10:39:48', '2024-10-23 14:53:04');
INSERT INTO `users`
VALUES (3, 'admin2', NULL, '$2a$10$DSqecdBi4B2Prrw7A.KbfOAXBkTvD2jU8nKz80nj7mBn7kN5UyjHa', '李浩', NULL, 0, 0,
        '2024-10-23 09:14:59', '2024-10-23 09:14:59');
INSERT INTO `users`
VALUES (4, 'admin3', NULL, '$2a$10$ObQZHE4VzNAQJI.j.POaf.JIZpJ7TdRfIKmD2W9ORu5iYpNiQVRYi', '李浩', NULL, 0, 0,
        '2024-10-23 16:07:15', '2024-10-23 16:07:15');

SET
FOREIGN_KEY_CHECKS = 1;
