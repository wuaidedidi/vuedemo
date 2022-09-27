package com.lichen.lichenblog.admin.controller;


import com.lichen.lichenblog.admin.service.ITagService;
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
@Api(tags = "文章标签")
@RestController
@RequestMapping("/admin/tags")
public class TagController extends BaseController {

    @Autowired
    ITagService tagService;
    /**
     * 搜索文章标签
     *
     * @param conditionVO 条件
     * @return {@link DabaituResult<String>} 标签列表
     */
    @ApiOperation(value = "搜索文章标签")
    @GetMapping("/search")
    public DabaituResult listTagsBySearch(ConditionVO conditionVO){
        return new DabaituResult().ok(tagService.listTagsBySearch(conditionVO));
    };
}
