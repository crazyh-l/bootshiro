package com.shirotest.bootshiro.realm;

import com.shirotest.bootshiro.model.User;
import com.shirotest.bootshiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String userName = (String)authenticationToken.getPrincipal();

        User user = userService.getUserByName(userName);

        if (user == null){
            throw  new UnknownAccountException("账户名不存在");
        }

        return new SimpleAuthenticationInfo(user.getAccount(),user.getPassword(),getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
