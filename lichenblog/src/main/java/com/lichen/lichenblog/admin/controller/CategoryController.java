package com.lichen.lichenblog.admin.controller;


import com.lichen.lichenblog.admin.service.ICategoryService;
import com.lichen.lichenblog.admin.util.DabaituResult;
import com.lichen.lichenblog.admin.vo.ConditionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.lichen.lichenblog.admin.controller.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lichen
 * @since 2022-09-13
 */
@Api(tags = "分类模块")
@RestController
@RequestMapping("/admin/categories")
public class CategoryController extends BaseController {
    @Autowired
    ICategoryService categoryService;
    @ApiOperation(value = "搜索文章分类")
    @GetMapping("search")
    public DabaituResult search(ConditionVO conditionVO){
      return new DabaituResult().ok(categoryService.listCategoriesBySearch(conditionVO));
    }


}
