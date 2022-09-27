package com.lichen.lichenblog.admin.mapper;

import com.lichen.lichenblog.admin.entity.Menu;
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
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> listMenusByUserInfoId(Integer userInfoId);
}
