package com.mdtg.robot.module.user.config;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author WangYunwei [2026-03-12]
 * 创建JwtToken存储用户/令牌
 * 继承AuthenticationToken，跟AccountRealmh中的doGetAuthenticationInfo的参数类型保持一致
 */
public class JwtToken implements AuthenticationToken {

    private String username;
    private String token;

    public JwtToken(String token){
        this.token = token;
        JwtUtil jwtUtil = new JwtUtil();
        this.username = jwtUtil.getClaimFiled(token, "username");
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
