package com.lichen.lichenblog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lichen.lichenblog.admin.dto.CategoryOptionDTO;
import com.lichen.lichenblog.admin.entity.Category;
import com.lichen.lichenblog.admin.mapper.CategoryMapper;
import com.lichen.lichenblog.admin.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lichen.lichenblog.admin.util.BeanCopyUtils;
import com.lichen.lichenblog.admin.vo.ConditionVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lichen
 * @since 2022-09-13
 */

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
    @Autowired
    CategoryMapper categoryDao;
    @Override
    public List<CategoryOptionDTO> listCategoriesBySearch(ConditionVO conditionVO) {
        List<Category> categories=categoryDao.selectList(new LambdaQueryWrapper<Category>()
        .like(StringUtils.isNotBlank(conditionVO.getKeywords()),Category::getCategoryName,conditionVO.getKeywords()));
        return BeanCopyUtils.copyList(categories,CategoryOptionDTO.class);
    }
}
