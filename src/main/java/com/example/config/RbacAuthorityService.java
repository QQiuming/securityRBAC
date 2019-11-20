package com.example.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.example.mapper.UserMapper;
import com.example.model.Permission;
import com.example.model.RolePermission;

@Component("rbacauthorityservice")
public class RbacAuthorityService {

    @Autowired
    UserMapper userMapper;

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        Object userInfo = authentication.getPrincipal();

        boolean hasPermission = false;

        if (userInfo instanceof UserDetails) {

            String username = ((UserDetails)userInfo).getUsername();

            // 获取资源
            Set<String> urls = new HashSet<String>();
            List<RolePermission> permissions = userMapper.selectUserByLoginName(username).getRole().getPermissions();
            
              permissions.forEach((rolePermission) -> {
                urls.add(rolePermission.getPermission().getUrl());// 这些 url 都是要登录后才能访问，且其他的 url 都不能访问！
            });

            AntPathMatcher antPathMatcher = new AntPathMatcher();

            for (String url : urls) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }

            return hasPermission;
        } else {
            return false;
        }
    }

}
