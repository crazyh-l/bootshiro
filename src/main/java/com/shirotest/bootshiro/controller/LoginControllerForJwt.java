/*
package com.shirotest.bootshiro.controller;

import com.shirotest.bootshiro.model.User;
import com.shirotest.bootshiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginControllerForJwt {

    @Autowired
    private UserService userService;

    */
/**
     * 用户名密码登录
     * @return
     *//*

    @PostMapping(value = "/login")
    public ResponseEntity<Void> login(@RequestBody User loginInfo,
                                      HttpServletRequest request, HttpServletResponse response){
        Subject subject = SecurityUtils.getSubject();
        String userName = loginInfo.getUserName();
        String password = loginInfo.getPassWord();*/
/**//*

        try {
            UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
            subject.login(token);

            User user = (User)subject.getPrincipal();
            String newToken = userService.generateJwtToken(userName);
            response.setHeader("x-auth",newToken);

            return ResponseEntity.ok().build();
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    */
/**
     * 退出登录
     * @return
     *//*

    @GetMapping(value = "/logout")
    public ResponseEntity<Void> logout(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipals() != null) {
            User user = (User) subject.getPrincipals().getPrimaryPrincipal();
           // 删除数据库或缓存中存在的salt;
        }
        subject.logout();
        return ResponseEntity.ok().build();
    }

}
*/
