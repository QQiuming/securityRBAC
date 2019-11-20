package com.example.model;

import java.util.List;

import lombok.Data;

@Data
public class Role {

    private Integer roleid;
    private String rolename;
    private String roleDescription;
    private List<RolePermission> permissions;
    
}
