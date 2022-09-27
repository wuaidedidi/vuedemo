package com.lichen.lichenblog.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.lichen.lichenblog.admin.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("tb_category")
public class Category extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 分类名
     */
    private String categoryName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
