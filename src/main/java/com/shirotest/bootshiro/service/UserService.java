package com.shirotest.bootshiro.service;

import com.shirotest.bootshiro.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getUserById(Long id);

    User getUserByName(String userName);

    void insert(User user);

    void update(User user);

    void delete(Long id);

    Long getMaxUserId();
}
