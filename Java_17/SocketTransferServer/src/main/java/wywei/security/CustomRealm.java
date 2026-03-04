package wywei.security;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

/**
 * @author WangYunwei [2026-02-27]
 */
@Component
public class CustomRealm extends AuthorizingRealm {

    /**
     * 授权/获取权限信息 (控制谁能看什么)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        // TODO: 根据用户名查询数据库/缓存，获取角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole("admin"); // 模拟赋予 admin 角色
        authorizationInfo.addStringPermission("user:delete");
        return authorizationInfo;
    }

    /**
     * 认证/登录 (控制谁能登录)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String username = (String) authenticationToken.getPrincipal();
        // TODO: 根据用户名查询数据库获取用户信息
        // User user = userService.findByUsername(username);

        // 2. 模拟：假设用户存在，密码是 123456 (实际需查库)
        if ("admin".equals(username)) {
            // 参数：用户名，数据库密码，盐值（若无则null），当前 realm 名称
            return new SimpleAuthenticationInfo(username, "123456", getName());
        } else {
            throw new UnknownAccountException("用户不存在");
        }
    }
}
