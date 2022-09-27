package com.lichen.lichenblog.admin.handler;

import com.alibaba.fastjson.JSON;
import com.lichen.lichenblog.admin.util.DabaituResult;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.lichen.lichenblog.admin.constant.CommonConstant.APPLICATION_JSON;


/**
 * 登录失败处理
 *
 * @author yezhiqiu
 * @date 2021/07/28
 */
@Component
public class AuthenticationFailHandlerImpl implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setContentType(APPLICATION_JSON);
        httpServletResponse.getWriter().write(JSON.toJSONString(new DabaituResult().isFail().Message(e.getMessage())));
    }

}
