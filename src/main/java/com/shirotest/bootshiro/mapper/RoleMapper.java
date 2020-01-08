package com.shirotest.bootshiro.mapper;

import com.shirotest.bootshiro.model.Role;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {

    Role getRoleById(@Param("id") int id);
}
