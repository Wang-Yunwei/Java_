package com.mdtg.robot.common.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdtg.robot.common.exception.BusinessException;
import com.mdtg.robot.module.user.entity.User;
import com.mdtg.robot.module.user.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
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
        log.info("123123123123");
        String jwt = (String) authenticationToken.getCredentials();
        // 获取jwt中关于用户名
        String phone = jwtUtil.getClaimsByToken(jwt).getSubject();
        // 查询用户
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getPhone, phone));
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        if (user.getStatus() == 1) {
            throw new BusinessException("用户被锁定");
        }
        Claims claims = jwtUtil.getClaimsByToken(jwt);
        if (jwtUtil.isTokenExpired(claims.getExpiration())) {
            throw new BusinessException("token过期,请重新登录!");
        }
        return new SimpleAuthenticationInfo(user, jwt, getName());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        // 明确声明支持 JwtToken 类型的 Token
        return token instanceof JwtToken;
    }
}
