package com.lichen.lichenblog.admin.mapper;

import com.lichen.lichenblog.admin.dto.ResourceRoleDTO;
import com.lichen.lichenblog.admin.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lichen
 * @since 2022-09-13
 */
public interface RoleMapper extends BaseMapper<Role> {


    /**
     * 查询路由角色列表
     *
     * @return 角色标签
     */
    List<ResourceRoleDTO> listResourceRoles();

    List<String> listRolesByUserInfoId(Integer id);
}
