package com.example.model;

import lombok.Data;

@Data
public class User {

    private Integer userid;
    private String loginname;
    private String password;
    private String userDescription;
    private Integer roleid;
    private Role role;
    
}
