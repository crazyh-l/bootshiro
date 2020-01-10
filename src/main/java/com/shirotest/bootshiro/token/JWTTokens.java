package com.shirotest.bootshiro.token;

import org.apache.shiro.authc.HostAuthenticationToken;

public class JWTTokens implements HostAuthenticationToken {

    private String token;

    private String host;

    public JWTTokens(String token){
        this(token,null);
    }

    public JWTTokens(String token, String host){
        this.token = token;
        this.host = host;
    }

    public String getToken(){
        return this.token;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
