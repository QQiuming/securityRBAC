package com.example.handler;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.example.model.SecurityUser;
import com.example.resp.CommonResp;
import com.example.utils.JwtTokenUtil;

/* AuthenticationSuccessHandler   登录验证成功 */
@Component
public class GoAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Value("${jwt.tokenPrefix}")
    private String tokenPrefix;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        // TODO Auto-generated method stub
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        SecurityUser securityUser = (SecurityUser)authentication.getPrincipal();
        String token = tokenPrefix+" "+jwtTokenUtil.generateToken(securityUser);
        // 返回创建成功的token
        // 但是这里创建的token只是单纯的token
        // 按照jwt的规定，最后请求的时候应该是 `Bearer token`
       
        CommonResp commonResp = new CommonResp();
        commonResp.setCode("200");
        commonResp.setMsg("login success");
        commonResp.setObj(token);
        response.getWriter().write(JSON.toJSONString(commonResp));
        response.getWriter().flush();
    }

}
