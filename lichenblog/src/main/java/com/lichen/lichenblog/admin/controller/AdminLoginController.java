package com.lichen.lichenblog.admin.controller;

import com.lichen.lichenblog.admin.util.DabaituResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lichen
 * @version 1.0.0
 * @className AdminLoginController.java
 * @Description TODO
 * @createTime 2022年09月13日 15:51:00
 */
@Api(tags = "后台登录模块")
@RestController
public class AdminLoginController {

    // @PostMapping("/login")
    // public DabaituResult login (HttpServletRequest req, HttpServletResponse res){
    //         System.out.println(222);
    //     return new DabaituResult().Message("大白猪").Ok();
    // }
}
