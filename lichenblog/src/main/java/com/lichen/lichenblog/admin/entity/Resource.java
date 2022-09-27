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
@TableName("tb_resource")
public class Resource extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 资源名
     */
    private String resourceName;

    /**
     * 权限路径
     */
    private String url;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 父权限id
     */
    private Integer parentId;

    /**
     * 是否匿名访问 0否 1是
     */
    private Boolean isAnonymous;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


}
