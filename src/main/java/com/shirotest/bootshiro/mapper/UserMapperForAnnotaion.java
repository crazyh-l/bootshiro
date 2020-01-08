package com.shirotest.bootshiro.mapper;

import com.shirotest.bootshiro.enums.UserSexEnum;
import com.shirotest.bootshiro.model.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapperForAnnotaion {

    @Select("SELECT * FROM users")
    @Results({
            @Result(property = "userSex",column = "user_sex",javaType = UserSexEnum.class),
            @Result(property = "nickName",column = "nick_name")
    })
    List<User> getAll();

    @Select("SELECT * FROM users where id = #{id}")
    @Results({
            @Result(property = "userSex",column = "user_sex",javaType = UserSexEnum.class),
            @Result(property = "nickName",column = "nick_name")
    })
    User getOne(Long id);

}
