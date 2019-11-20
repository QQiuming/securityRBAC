package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mapper.PermissionMapper;
import com.example.mapper.RolePermissionMapper;
import com.example.mapper.UserMapper;
import com.example.model.RolePermission;
import com.example.model.User;
import com.example.resp.CommonResp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "测试接口")
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    UserMapper userMapper;
    
    @Autowired
    RolePermissionMapper rolePermissionMapper;
    
    @Autowired
    PermissionMapper permissionMapper;
    
    @ApiOperation(value = "hello", notes = "hello", httpMethod = "GET")
    @GetMapping("/hello")
    public CommonResp hello() {

        return new CommonResp("200", "hello", null);
    }
    
    @ApiOperation(value = "selectUser", notes = "selectUser", httpMethod = "POST")
    @PostMapping("/selectUser")
    public CommonResp selectUser(@RequestParam("loginname") String loginname) {
        User user = userMapper.selectUserByLoginName(loginname);
        List<RolePermission> permissions = rolePermissionMapper.selectRolePermissionByRoleid(3);
        permissionMapper.selectPermissionByPermissionid(2);
        return new CommonResp("200", "success", user);
    }
    
    @ApiOperation(value = "testpermissionA", notes = "testpermissionA", httpMethod = "GET")
    @GetMapping("/permissionA")
    public CommonResp testpermissionA(@RequestParam("loginname") String loginname) {
        User user = userMapper.selectUserByLoginName(loginname);
        return new CommonResp("200", "success", user);
    }
}
