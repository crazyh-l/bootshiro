package com.shirotest.bootshiro.service.impl;

import com.shirotest.bootshiro.mapper.UserMapper;
import com.shirotest.bootshiro.mapper.UserMapperForAnnotaion;
import com.shirotest.bootshiro.model.User;
import com.shirotest.bootshiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final String encryptSalt = "F12839WhsnnEV$#23b";

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserMapperForAnnotaion userMapperForAnnotaion;

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
        //return userMapperForAnnotaion.getAll();
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.getOne(id);
    }

    @Override
    public User getUserByName(String userName) {
      return userMapper.getUserByName(userName);
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void delete(Long id) {
        userMapper.delete(id);
    }

    @Override
    public Long getMaxUserId() {
        return userMapper.getMaxUserId();
    }


}

