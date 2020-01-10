package com.shirotest.bootshiro.realm;

import com.shirotest.bootshiro.model.User;
import com.shirotest.bootshiro.model.UserEntity;
import com.shirotest.bootshiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {



    public static Map<String, UserEntity> userMap = new HashMap<String, UserEntity>(16);
    public static Map<String, Set<String>> roleMap = new HashMap<String, Set<String>>(16);
    public static Map<String, Set<String>> permMap = new HashMap<String, Set<String>>(16);

    static {
        UserEntity user1 = new UserEntity(1L, "graython", "dd524c4c66076d1fa07e1fa1c94a91233772d132", "灰先生", false);
        UserEntity user2 = new UserEntity(2L, "plum", "cce369436bbb9f0325689a3a6d5d6b9b8a3f39a0", "李先生", false);

        userMap.put("graython", user1);
        userMap.put("plum", user2);

        roleMap.put("graython", new HashSet<String>() {
            {
                add("admin");
            }
        });

        roleMap.put("plum", new HashSet<String>() {
            {
                add("guest");
            }
        });
        permMap.put("plum", new HashSet<String>() {
            {
                add("article:read");
            }
        });
    }


    @Autowired
    UserService userService;

    /**
     * 只验证 usernamepasswordtoken
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前用户
        UserEntity userEntity = (UserEntity) principalCollection;
        //查询数据库，获取用户的角色信息
        Set<String> roles = roleMap.get(userEntity.getName());
        //查询数据库，获取用户的权限信息
        Set<String> perms = permMap.get(userEntity.getName());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(perms);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String userName = (String)authenticationToken.getPrincipal();

        User user = userService.getUserByName(userName);
        if (user == null) {
            throw new UnknownAccountException("账户名不存在");
        }

        //使用用户名作为颜值
        ByteSource credentialsSalt = ByteSource.Util.bytes(userName);

        return new SimpleAuthenticationInfo(user,credentialsSalt,getName());
    }
}
