package com.example.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @ApiOperation(value = "userLogin", notes = "userLogin", httpMethod = "POST")
    @PostMapping("/login")
    public void login(@RequestParam("username") String loginname,@RequestParam("password") String password,@RequestParam("captcha") String captcha) {
        System.out.println(123);


    }
    
}
