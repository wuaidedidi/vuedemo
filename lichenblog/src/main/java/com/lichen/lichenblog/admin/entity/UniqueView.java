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
@TableName("tb_unique_view")
public class UniqueView extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 访问量
     */
    private Integer viewsCount;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
