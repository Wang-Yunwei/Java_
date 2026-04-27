package com.mdtg.robot.module.user.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdtg.robot.module.user.entity.User;
import com.mdtg.robot.module.user.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

/**
 * @author WangYunwei [2026-03-12]
 */
@Slf4j
@Component
public class CustomRealm extends AuthorizingRealm {

    private final UserService userService;

    @Resource
    private JwtUtil jwtUtil;

    public CustomRealm(UserService userService) {

        this.userService = userService;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        log.info("doGetAuthenticationInfo");
        String token = (String) authenticationToken.getCredentials();
        // 获取jwt中关于用户名        String phone = jwtUtil.getClaimsByToken(token).getSubject();
        // 查询用户
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getPhone, phone));
        if (user == null) {
            throw new UnknownAccountException("账户不存在!");
        }
        if (user.getStatus() == 1) {
            throw new LockedAccountException("账号已被锁定，请稍后重试!");
        }
        if (jwtUtil.isTokenExpired(token)) {
            throw new ExpiredCredentialsException("Token已过期");
        }
        return new SimpleAuthenticationInfo(user, token, getName());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        // 明确声明支持 JwtToken 类型的 Token
        return token instanceof JwtToken;
    }
}
