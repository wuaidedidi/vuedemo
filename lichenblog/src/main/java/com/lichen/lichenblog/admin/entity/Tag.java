package com.lichen.lichenblog.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.lichen.lichenblog.admin.entity.BaseEntity;

import java.time.LocalDateTime;

import lombok.*;

/**
 * 标签
 *
 * @author yezhiqiu
 * @date 2021/08/10
 * @since 2020-05-18
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_tag")
public class Tag {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标签名
     */
    private String tagName;

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
