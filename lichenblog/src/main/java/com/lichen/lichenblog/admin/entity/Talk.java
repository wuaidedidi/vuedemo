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
@TableName("tb_talk")
public class Talk extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 说说内容
     */
    private String content;

    /**
     * 图片
     */
    private String images;

    /**
     * 是否置顶
     */
    private Boolean isTop;

    /**
     * 状态 1.公开 2.私密
     */
    private Boolean status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
