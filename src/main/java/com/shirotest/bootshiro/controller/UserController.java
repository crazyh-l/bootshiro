package com.shirotest.bootshiro.controller;

import com.shirotest.bootshiro.model.Permission;
import com.shirotest.bootshiro.model.Role;
import com.shirotest.bootshiro.model.User;
import com.shirotest.bootshiro.response.ResponseResult;
import com.shirotest.bootshiro.service.PermissionService;
import com.shirotest.bootshiro.service.RoleService;
import com.shirotest.bootshiro.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @PostMapping("/getUserById")
    @ResponseBody
    public User getUserById(String userId){

        if (StringUtils.isNotBlank(userId)) {
            User user = userService.getUserById(Long.valueOf(userId));
            Role role = roleService.getRoleById(user.getRoleId());
            Permission permission = permissionService.getPermissionById(user.getPermissionId());
            user.setRoles(role);
            user.setPermissions(permission);
            return user;
        }
        return null;
    }


    @PostMapping("/updateUserInfo")
    @ResponseBody
    public ResponseResult updateUserInfo(User user) {
        if (user != null) {
            userService.update(user);
        }
        return new ResponseResult(200,"修改成功");
    }

    @PostMapping("/deleteUser")
    @ResponseBody
    public ResponseResult deleteUser(String id){
        if (id != null) {
            userService.delete(Long.valueOf(id));
        }
        return new ResponseResult(200,"删除成功");
    }

    @PostMapping("/addUser")
    @ResponseBody
    public ResponseResult addUser(User user){
        if (user != null) {
            Long maxId = userService.getMaxUserId();
            user.setId(maxId);
            userService.insert(user);
        }
        return new ResponseResult(200,"新增成功");
    }

}
