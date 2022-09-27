package com.lichen.lichenblog.admin.entity;

import com.lichen.lichenblog.admin.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lichen
 * @since 2022-09-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_friend_link")
public class FriendLink extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 链接名
     */
    private String linkName;

    /**
     * 链接头像
     */
    private String linkAvatar;

    /**
     * 链接地址
     */
    private String linkAddress;

    /**
     * 链接介绍
     */
    private String linkIntro;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
