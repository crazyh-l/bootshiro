package com.shirotest.bootshiro.service.impl;

import com.shirotest.bootshiro.mapper.RoleMapper;
import com.shirotest.bootshiro.model.Role;
import com.shirotest.bootshiro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServeImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public Role getRoleById(int id) {
        return roleMapper.getRoleById(id);
    }
}
