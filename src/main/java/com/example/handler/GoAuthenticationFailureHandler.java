package com.example.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.example.resp.CommonResp;
/*
 *  AuthenticationFailureHandler 如果身份验证失败时调用（token无效    ）
 */
@Component
public class GoAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        // TODO Auto-generated method stub
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        CommonResp commonResp = new CommonResp();
        commonResp.setCode("401");
        commonResp.setMsg("authentication failure");
        response.getWriter().write(JSON.toJSONString(commonResp));
        response.getWriter().flush();
    }

}
