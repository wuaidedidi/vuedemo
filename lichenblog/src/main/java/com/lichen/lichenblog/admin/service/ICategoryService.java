package com.lichen.lichenblog.admin.service;

import com.lichen.lichenblog.admin.dto.CategoryOptionDTO;
import com.lichen.lichenblog.admin.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lichen.lichenblog.admin.vo.ConditionVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lichen
 * @since 2022-09-13
 */
public interface ICategoryService extends IService<Category> {

    List<CategoryOptionDTO> listCategoriesBySearch(ConditionVO conditionVO);
}
