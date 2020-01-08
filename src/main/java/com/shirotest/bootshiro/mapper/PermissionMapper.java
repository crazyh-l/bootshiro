package com.shirotest.bootshiro.mapper;

import com.shirotest.bootshiro.model.Permission;
import org.apache.ibatis.annotations.Param;

public interface PermissionMapper {

    Permission getPermissionById(@Param("id")int id);
}
