package com.example.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.service.JwtUserDetailsService;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    
    /**
     * 进行身份认证
     *
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // TODO Auto-generated method stub
        // 获取用户输入的用户名和密码
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        // 获取封装用户信息的对象
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
        // 进行密码的比对
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
     
        boolean flag = bCryptPasswordEncoder.matches(password, userDetails.getPassword());
        // 校验通过
        if (flag){
            // 将权限信息也封装进去
            return new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
        }

        // 验证失败返回 null
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // TODO Auto-generated method stub
        return true;
    }

}
