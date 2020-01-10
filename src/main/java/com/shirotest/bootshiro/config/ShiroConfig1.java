package com.shirotest.bootshiro.config;

import com.shirotest.bootshiro.realm.LoginRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

//@Configuration
public class ShiroConfig1 {

    /*@Bean
    LoginRealm getLoginRealm(){
        return new LoginRealm();
    }

    @Bean
    DefaultWebSecurityManager  securityManager(){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(getLoginRealm());
        return manager;
    }

    @Bean
    ShiroFilterChainDefinition shiroFilterChainDefinition(){
        DefaultShiroFilterChainDefinition shiroFilterChainDefinition
                = new DefaultShiroFilterChainDefinition();
        shiroFilterChainDefinition.addPathDefinition("/doLogin","anon");
        shiroFilterChainDefinition.addPathDefinition("/**","anon");
        return shiroFilterChainDefinition;
    }*/

}

