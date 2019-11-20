package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.mapper.UserMapper;
import com.example.model.SecurityUser;
import com.example.model.User;

@Service("jwtUserDetailsService")
public class JwtUserDetailsService implements UserDetailsService{

    @Autowired
    UserMapper userMapper;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
       
        User user = userMapper.selectUserByLoginName(username);
        if (null == user) {
            return null;
        }
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_"+ user.getRole().getRolename()));
        
        return new SecurityUser(username,user.getPassword(), authorityList);
    }

}
