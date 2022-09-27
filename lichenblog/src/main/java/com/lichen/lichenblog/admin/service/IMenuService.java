package com.lichen.lichenblog.admin.service;

import com.lichen.lichenblog.admin.dto.UserMenuDTO;
import com.lichen.lichenblog.admin.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lichen
 * @since 2022-09-13
 */
public interface IMenuService extends IService<Menu> {
    /**
     * 查看用户菜单
     *
     * @return 菜单列表
     */
    List<UserMenuDTO> listUserMenus();
}
