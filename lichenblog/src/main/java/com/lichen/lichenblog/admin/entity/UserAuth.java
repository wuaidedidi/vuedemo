package com.lichen.lichenblog.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.lichen.lichenblog.admin.entity.BaseEntity;

import java.time.LocalDateTime;

import lombok.*;

/**
 * 用户账号
 *
 * @author yezhiqiu
 * @date 2021/08/01
 * @since 2020-05-18
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_user_auth")
public class UserAuth extends BaseEntity{

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户信息id
     */
    private Integer userInfoId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 登录类型
     */
    private Integer loginType;

    /**
     * 用户登录ip
     */
    private String ipAddress;

    /**
     * ip来源
     */
    private String ipSource;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 最近登录时间
     */
    private LocalDateTime lastLoginTime;

}
