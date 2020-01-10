package com.shirotest.bootshiro.controller;

import com.shirotest.bootshiro.Utils.JwtUtils;
import com.shirotest.bootshiro.mapper.BaseResponse;
import com.shirotest.bootshiro.model.Permission;
import com.shirotest.bootshiro.model.Role;
import com.shirotest.bootshiro.model.User;
import com.shirotest.bootshiro.response.ResponseResult;
import com.shirotest.bootshiro.service.PermissionService;
import com.shirotest.bootshiro.service.RoleService;
import com.shirotest.bootshiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
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


    @PostMapping(value = "/login")
    @ResponseBody
    public Object userLogin(@RequestParam(name = "username", required = true) String userName,
                            @RequestParam(name = "password", required = true) String password, ServletResponse response) {

        // 获取当前用户主体
        Subject subject = SecurityUtils.getSubject();
        String msg = null;
        boolean loginSuccess = false;
        // 将用户名和密码封装成 UsernamePasswordToken 对象
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        try {
            subject.login(token);
            msg = "登录成功。";
            loginSuccess = true;
        } catch (UnknownAccountException uae) { // 账号不存在
            msg = "用户名与密码不匹配，请检查后重新输入！";
        } catch (IncorrectCredentialsException ice) { // 账号与密码不匹配
            msg = "用户名与密码不匹配，请检查后重新输入！";
        } catch (LockedAccountException lae) { // 账号已被锁定
            msg = "该账户已被锁定，如需解锁请联系管理员！";
        } catch (AuthenticationException ae) { // 其他身份验证异常
            msg = "登录异常，请联系管理员！";
        }
        BaseResponse<Object> ret = new BaseResponse<Object>();
        if (loginSuccess) {
            // 若登录成功，签发 JWT token
            String jwtToken = JwtUtils.sign(userName, JwtUtils.secret_key);
            // 将签发的 JWT token 设置到 HttpServletResponse 的 Header 中
            ((HttpServletResponse) response).setHeader(JwtUtils.AUTH_HEADER, jwtToken);
            //
            ret.setErrCode(0);
            ret.setMsg(msg);
            return ret;
        } else {
            ret.setErrCode(401);
            ret.setMsg(msg);
            return ret;
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
