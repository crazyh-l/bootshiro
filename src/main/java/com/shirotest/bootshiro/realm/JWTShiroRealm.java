/*
package com.shirotest.bootshiro.realm;

import com.shirotest.bootshiro.Utils.JwtUtils;
import com.shirotest.bootshiro.matcher.JWTCredentialsMatcher;
import com.shirotest.bootshiro.model.User;
import com.shirotest.bootshiro.service.UserService;
import com.shirotest.bootshiro.token.JWTToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class JWTShiroRealm extends AuthorizingRealm {

    @Autowired
    protected UserService userService;

    public JWTShiroRealm(UserService userService){
        this.userService = userService;
        //使用自定义的Matcher
        this.setCredentialsMatcher(new JWTCredentialsMatcher());
    }

    */
/**
     * 限定这个Realm只支持我们定义的JWT token
     * @param token
     * @return
     *//*

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        JWTToken jwtToken = (JWTToken) authenticationToken;
        String token = jwtToken.getToken();

        User  user = userService.getJwtTokenInfo(JwtUtils.getUsername(token));
        if (user == null) {
            throw new AuthenticationException("token过期，请重新登录");
        }

        SimpleAuthenticationInfo simpleInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getSalt(), "jwtRealm");

        return null;
    }
}
*/
