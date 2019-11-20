package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.model.Permission;

@Mapper
public interface PermissionMapper {

    Permission selectPermissionByPermissionid(Integer permissionid);
}
