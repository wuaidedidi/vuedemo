package com.lichen.lichenblog.admin.handler;

import com.alibaba.fastjson.JSON;
import com.lichen.lichenblog.admin.util.DabaituResult;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.lichen.lichenblog.admin.constant.CommonConstant.APPLICATION_JSON;


/**
 * 用户权限处理
 *
 * @author yezhiqiu
 * @date 2021/07/28
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        httpServletResponse.setContentType(APPLICATION_JSON);
        httpServletResponse.getWriter().write(JSON.toJSONString(new DabaituResult().isFail().Message("权限不足")));
    }

}
