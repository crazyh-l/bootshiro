package com.shirotest.bootshiro.controller;

import com.shirotest.bootshiro.model.Permission;
import com.shirotest.bootshiro.model.Role;
import com.shirotest.bootshiro.model.User;
import com.shirotest.bootshiro.response.ResponseResult;
import com.shirotest.bootshiro.service.PermissionService;
import com.shirotest.bootshiro.service.RoleService;
import com.shirotest.bootshiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PermissionService permissionService;

    @PostMapping("/doLogin")
    @ResponseBody
    public ResponseResult doLogin(String account, String password){
        Subject subject = SecurityUtils.getSubject();
        ResponseResult responseResult = new ResponseResult();
        try {
            subject.login(new UsernamePasswordToken(account, password));
            User user = userService.getUserByName(account);
            if (user != null) {
                Role role = roleService.getRoleById(user.getRoleId());
                Permission permission = permissionService.getPermissionById(user.getPermissionId());
                user.setRoles(role);
                user.setPermissions(permission);
                responseResult.setCode(200);
                responseResult.setMsg("登录成功");
                responseResult.setObj(user);
            }
            return responseResult;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("登录失败!");
            return new ResponseResult(304,"登录失败");
        }
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/login")
    public String  login() {
        return "index";
    }

    @PostMapping("/getAllUser")
    @ResponseBody
    public List<User> getAllUser(){

        List<User> users = userService.getAll();
        users.stream().forEach(item -> {
            System.out.print(item);
        });
        return users;
    }

}
