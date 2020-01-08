/*
package com.shirotest.bootshiro.realm;

import com.shirotest.bootshiro.model.User;
import com.shirotest.bootshiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class DbShiroRealm extends AuthorizingRealm {


    private static final String encryptSalt = "yh123";
    @Autowired
    private UserService userService;

    public DbShiroRealm(UserService userService){
        this.userService = userService;
        //使用shiro的散列Matcher
        this.setCredentialsMatcher(new HashedCredentialsMatcher(Sha256Hash.ALGORITHM_NAME));
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        List<String> roles = user.getRoles();

        if (roles == null) {
            roles = userService.getUserRoles(user.getId());
            user.setRoles(roles);
        }
        if (roles != null) {
            simpleAuthorizationInfo.addRoles(roles);
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        User user = userService.getOne(12l);
        if (user == null) {
            throw new AuthenticationException("用户不存在");
        }

        return new SimpleAuthenticationInfo(user,user.getPassWord(), ByteSource.Util.bytes(encryptSalt),getName());
    }
}
*/
