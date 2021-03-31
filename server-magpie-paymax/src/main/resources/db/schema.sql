-- DROP TABLE IF EXISTS `Channel`;
--
-- SET FOREIGN_KEY_CHECKS = 0;
-- DROP TABLE IF EXISTS `Student`;
-- DROP TABLE IF EXISTS `StudentScore`;
-- SET FOREIGN_KEY_CHECKS = 1;

# DROP TABLE IF EXISTS `notes`;
# CREATE TABLE IF NOT EXISTS `notes`
# (
#     `id`         INT          NOT NULL AUTO_INCREMENT COMMENT '主键',
#     `title`      VARCHAR(255) NOT NULL UNIQUE COMMENT '渠道名',
#     `content`    VARCHAR(255) NOT NULL UNIQUE COMMENT '渠道名',
#     `created_at` TIMESTAMP    NULL DEFAULT NOW() COMMENT '创建时间',
#     `updated_at` TIMESTAMP    NULL DEFAULT NOW() COMMENT '上次更新时间',
#     PRIMARY KEY (`id`)
# ) ENGINE = InnoDB
#   DEFAULT CHARSET = utf8mb4 COMMENT = '测试 Note';
#
#
# DROP TABLE IF EXISTS `jpa_entity`;
# CREATE TABLE IF NOT EXISTS `jpa_entity`
# (
#     `id`      INT NOT NULL AUTO_INCREMENT COMMENT '主键',
#     `user_id` INT NOT NULL COMMENT '用户 ID',
#     PRIMARY KEY (`id`)
# ) ENGINE = InnoDB
#   DEFAULT CHARSET = utf8mb4 COMMENT = '测试 JPA';
#
#
# DROP TABLE IF EXISTS `channel`;
# CREATE TABLE IF NOT EXISTS `channel`
# (
#     `id`         INT          NOT NULL AUTO_INCREMENT COMMENT '主键',
#     `user_id`    INT          NOT NULL COMMENT '用户 ID',
#     `name`       VARCHAR(255) NOT NULL UNIQUE COMMENT '渠道名',
#     `created_at` TIMESTAMP    NULL DEFAULT NOW() COMMENT '创建时间',
#     `updated_at` TIMESTAMP    NULL DEFAULT NOW() COMMENT '上次更新时间',
#     PRIMARY KEY (`id`)
# ) ENGINE = InnoDB
#   DEFAULT CHARSET = utf8mb4 COMMENT = '渠道表';
#
# DROP TABLE IF EXISTS `student`;
# CREATE TABLE IF NOT EXISTS `student`
# (
#     `id`         INT          NOT NULL AUTO_INCREMENT COMMENT '主键',
#     `user_id`    INT          NOT NULL COMMENT '用户 ID',
#     `channel_id` INT          NOT NULL COMMENT '渠道 ID',
#     `name`       VARCHAR(255) NOT NULL UNIQUE COMMENT '姓名唯一(测试)',
#     `age`        INT               DEFAULT NULL COMMENT '年龄',
#     `avatar`     VARCHAR(255)      DEFAULT NULL COMMENT '用户头像',
#     `created_at` TIMESTAMP    NULL DEFAULT NOW() COMMENT '创建时间',
#     `updated_at` TIMESTAMP    NULL DEFAULT NOW() COMMENT '上次更新时间',
#     PRIMARY KEY (`id`)
# ) ENGINE = InnoDB
#   DEFAULT CHARSET = utf8mb4 COMMENT = '学生表';
#
# DROP TABLE IF EXISTS `student_score`;
# CREATE TABLE IF NOT EXISTS `student_score`
# (
#     `id`         INT          NOT NULL AUTO_INCREMENT COMMENT '主键',
#     `user_id`    INT          NOT NULL COMMENT '用户 ID',
#     `channel_id` INT          NOT NULL COMMENT '渠道 ID',
#     `student_id` INT          NOT NULL UNIQUE COMMENT '学生 ID',
#     `course`     VARCHAR(255) NOT NULL COMMENT '课程',
#     `score`      INT               DEFAULT 0 COMMENT '分数',
#     `created_at` TIMESTAMP    NULL DEFAULT NOW() COMMENT '创建时间',
#     `updated_at` TIMESTAMP    NULL DEFAULT NOW() COMMENT '上次更新时间',
#     PRIMARY KEY (`id`)
# ) ENGINE = InnoDB
#   DEFAULT CHARSET = utf8mb4 COMMENT = '学生成绩表';
#
# DROP TABLE IF EXISTS `orm_user`;
# CREATE TABLE `orm_user`
# (
#     `id`              INT(11)     NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
#     `name`            VARCHAR(32) NOT NULL UNIQUE COMMENT '用户名',
#     `password`        VARCHAR(32) NOT NULL COMMENT '加密后的密码',
#     `salt`            VARCHAR(32) NOT NULL COMMENT '加密使用的盐',
#     `email`           VARCHAR(32) NOT NULL UNIQUE COMMENT '邮箱',
#     `phone_number`    VARCHAR(15) NOT NULL UNIQUE COMMENT '手机号码',
#     `status`          INT(2)      NOT NULL DEFAULT 1 COMMENT '状态，-1：逻辑删除，0：禁用，1：启用',
#     `created_at`      DATETIME    NOT NULL DEFAULT NOW() COMMENT '创建时间',
#     `last_login_time` DATETIME             DEFAULT NULL COMMENT '上次登录时间',
#     `updated_at`      DATETIME    NOT NULL DEFAULT NOW() COMMENT '上次更新时间'
# ) ENGINE = InnoDB
#   DEFAULT CHARSET = utf8 COMMENT ='Spring Boot Demo Orm 系列示例表';
# #
# # DROP TABLE IF EXISTS `orm_department`;
# # CREATE TABLE `orm_department`
# # (
# #     `id`         INT(11)     NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
# #     `name`       VARCHAR(32) NOT NULL COMMENT '部门名称',
# #     `superior`   INT(11) COMMENT '上级id',
# #     `levels`     INT(11)     NOT NULL COMMENT '层级',
# #     `order_no`   INT(11)     NOT NULL DEFAULT 0 COMMENT '排序',
# #     `created_at` DATETIME    NOT NULL DEFAULT NOW() COMMENT '创建时间',
# #     `updated_at` DATETIME    NOT NULL DEFAULT NOW() COMMENT '上次更新时间'
# # ) ENGINE = InnoDB
# #   DEFAULT CHARSET = utf8 COMMENT ='Spring Boot Demo Orm 系列示例表';
