package com.mdtg.robot.common.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdtg.robot.common.exception.BusinessException;
import com.mdtg.robot.module.user.entity.User;
import com.mdtg.robot.module.user.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author WangYunwei [2026-03-12]
 */
@Component
public class AccountRealm extends AuthorizingRealm {

    @Resource
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;
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
        String jwt = (String) authenticationToken.getCredentials();
        // 获取jwt中关于用户名
        String username = jwtUtil.getClaimsByToken(jwt).getSubject();
        // 查询用户
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
//        if (!user.getEnabled()) {
//            throw new BaseException(ResponseCodeEnum.BAD_REQUEST, "用户被锁定");
//        }

        Claims claims = jwtUtil.getClaimsByToken(jwt);
        if (jwtUtil.isTokenExpired(claims.getExpiration())) {
            throw new BusinessException("token过期,请重新登录!");
        }
        return new SimpleAuthenticationInfo(user, jwt, getName());
    }
}
