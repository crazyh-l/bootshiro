package com.shirotest.bootshiro.mapper;

import com.shirotest.bootshiro.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    List<User> getAll();

    User getOne(Long id);

    void insert(User user);

    void update(User user);

    void delete(@Param("id") Long id);

    User getUserByName(@Param("userName") String userName);

    Long getMaxUserId();
}
