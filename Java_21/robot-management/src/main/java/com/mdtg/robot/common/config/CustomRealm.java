package com.mdtg.robot.common.config;

import com.mdtg.robot.module.user.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.lang.util.ByteSource;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author WangYunwei [2026-03-10]
 */
@RequiredArgsConstructor
public class CustomRealm extends AuthorizingRealm {

    final UserService userService;

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // TODO: 根据用户名查询数据库，获取角色和权限，并添加到 authorizationInfo 中
        // 示例：模拟 admin 用户拥有 admin 角色和 user:manage 权限
        if ("admin".equals(username)) {
            authorizationInfo.addRole("admin");
            authorizationInfo.addStringPermission("user:manage");
        }
        return authorizationInfo;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 1. 获取用户输入的用户名和凭证, UsernamePasswordToken 中的信息会原封不动的传到 AuthenticationToken 中
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        // TODO: 根据用户名去数据库查询真实用户信息
        // 这里仅做简单演示，实际应查询数据库并比对密码
        if (!"admin".equals(username) || !"123456".equals(password)) {
            throw new UnknownAccountException("用户名或密码错误");
        }
        // 2. 返回认证信息 (此处省略了加密匹配的配置)
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, ByteSource.Util.bytes(username), getName());
        return info;
    }
}
