package com.lichen.lichenblog.admin.controller;


import com.lichen.lichenblog.admin.dto.UserMenuDTO;
import com.lichen.lichenblog.admin.service.IMenuService;
import com.lichen.lichenblog.admin.util.DabaituResult;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.lichen.lichenblog.admin.controller.BaseController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lichen
 * @since 2022-09-13
 */
@RestController
@RequestMapping("/admin/menu")
@RequiredArgsConstructor
public class MenuController extends BaseController {

    private  final IMenuService menuService;
    /**
     * 查看当前用户菜单
     *
     * @return {@link DabaituResult<UserMenuDTO>} 菜单列表
     */
    @ApiOperation(value = "查看当前用户菜单")
    @GetMapping("/user")
    public DabaituResult listUserMenus() {
        return new DabaituResult().ok(menuService.listUserMenus());
    }
}
