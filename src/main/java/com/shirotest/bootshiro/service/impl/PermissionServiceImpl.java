package com.shirotest.bootshiro.service.impl;

import com.shirotest.bootshiro.mapper.PermissionMapper;
import com.shirotest.bootshiro.model.Permission;
import com.shirotest.bootshiro.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionMapper permissionMapper;
    @Override
    public Permission getPermissionById(int id) {
        return permissionMapper.getPermissionById(id);
    }
}
