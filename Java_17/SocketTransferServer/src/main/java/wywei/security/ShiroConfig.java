package wywei.security;

import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WangYunwei [2026-02-27]
 */
@Configuration
public class ShiroConfig {

    private final CustomRealm customRealm;

    public ShiroConfig(CustomRealm customRealm) {
        this.customRealm = customRealm;
    }

    /**
     * 1. 配置 SecurityManager
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm);
        return securityManager;
    }

    /**
     * 2. 配置过滤器链
     * 注意：SB3 中使用最新的 ShiroFilterChainDefinition
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

        // 配置不需要认证即可访问的路径
        chainDefinition.addPathDefinition("/login", "anon");
        chainDefinition.addPathDefinition("/public/**", "anon");

        // 配置登出
        chainDefinition.addPathDefinition("/logout", "logout");

        // 其他所有路径都需要认证
        chainDefinition.addPathDefinition("/**", "authc");

        return chainDefinition;
    }
}
