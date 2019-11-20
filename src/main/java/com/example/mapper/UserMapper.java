package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.model.User;

@Mapper
public interface UserMapper {

    void insert(User user);
    
    User selectUserByLoginName(String loginname);
}
