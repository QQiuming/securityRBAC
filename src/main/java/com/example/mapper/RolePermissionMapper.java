package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.model.RolePermission;

@Mapper
public interface RolePermissionMapper {

    List<RolePermission> selectRolePermissionByRoleid(Integer roleid);
}
