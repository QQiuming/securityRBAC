package com.example.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.example.resp.CommonResp;

/*
    AccessDeineHandler 用来解决认证过的用户访问无权限资源时的异常
*/

@Component
public class GoAccessDeniedHandler implements AccessDeniedHandler{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
        AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // TODO Auto-generated method stub
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.setStatus(403);
        CommonResp commonResp = new CommonResp();
        commonResp.setCode("403");
        commonResp.setMsg("No Permission");
        response.getWriter().write(JSON.toJSONString(commonResp));
        response.getWriter().flush();
    }

}
