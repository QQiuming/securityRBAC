package com.example.model;

import lombok.Data;

@Data
public class RolePermission {

    private Integer roleid;
    private Integer permissionid;
    private Permission permission;
    
}
