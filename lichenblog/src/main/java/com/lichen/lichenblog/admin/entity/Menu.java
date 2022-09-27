package com.lichen.lichenblog.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.lichen.lichenblog.admin.entity.BaseEntity;

import java.time.LocalDateTime;

import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author lichen
 * @since 2022-09-13
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_menu")
public class Menu extends BaseEntity {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 菜单名
     */
    private String name;

    /**
     * 路径
     */
    private String path;

    /**
     * 组件
     */
    private String component;

    /**
     * icon
     */
    private String icon;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 父id
     */
    private Integer parentId;

    /**
     * 是否隐藏
     */
    private Integer isHidden;

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


}
