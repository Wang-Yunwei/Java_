package com.mdtg.robot.module.user.config;

import jakarta.servlet.Filter;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author WangYunwei [2026-03-12]
 */

@Configuration
public class ShiroConfig {

    @Bean
    public DefaultWebSessionManager sessionManager() {
        // 创建一个 DefaultWebSessionManager 实例,这是 Shiro 为 Web 环境提供的默认会话管理器
        // 它不依赖于 Servlet 容器(如 Tomcat)的会话,而是由 Shiro 自己维护会话的生命周期
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        /**
         * 禁用会话验证调度器。
         * -作用: Shiro 默认会启动一个后台线程定时器(调度器),定期检查并清理过期的会话
         * -为什么要禁用：在使用 JWT 等无状态 Token 的认证机制时，服务器端通常不保存会话状态（即“无状态”）。
         * Token 的有效性和过期时间由 Token 本身（如 JWT 的 payload）决定，而不是由服务器端的会话记录决定。
         * 因此，服务器端无需维护和清理会话，禁用该功能可以减少不必要的后台线程和资源消耗
         */
        sessionManager.setSessionValidationSchedulerEnabled(false);
        /**
         * 禁用 URL 重写功能。
         * -作用: 这是 Shiro 的一种会话 ID 传递机制。当浏览器禁用 Cookie 时，Shiro 会尝试将会话 ID 附加在 URL 的后面（如 ;jsessionid=xxx）来维持会话。
         * -为什么要禁用：在前后端分离或使用 Token 的架构中，会话 ID（或 Token）通常通过 HTTP 请求头（如 Authorization 头）传递，而不是通过 Cookie 或 URL 重写。
         * 禁用 URL 重写可以防止会话 ID 暴露在 URL 中，提高安全性，并避免 URL 被修改
         */
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }

    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(CustomRealm customRealm, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm);
        securityManager.setSessionManager(sessionManager);
        securityManager.setRememberMeManager(null);
        return securityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        /**
         *  注册jwt过滤器，除/login外都先经过jwtFilter
         */
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JwtFilter());
        shiroFilter.setFilters(filterMap);

        // 指定过滤路径
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("/doc.html", "anon");
        map.put("/webjars/**", "anon");
        map.put("/v3/api-docs/**", "anon");
        map.put("/favicon.ico", "anon");

        map.put("/login", "anon");
        map.put("/**", "jwt");
        shiroFilter.setFilterChainDefinitionMap(map);
        return shiroFilter;
    }

    /**
     * 解决@RequiresAuthentication注解不生效的配置
     */
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 自动代理
     *
     * @DependsOn({"lifecycleBeanPostProcessor"}) 指定当前 Bean 的初始化依赖于名为 lifecycleBeanPostProcessor 的 Bean
     * 默认情况: Spring 默认使用 JDK 动态代理,但这要求目标类必须实现至少一个接口,代理对象是接口的实现类,而不是目标类的子类
     * 设置为 true: 强制 Spring 使用 CGLIB 代理,CGLIB 会通过继承目标类的方式创建代理,这意味着即使目标类没有实现接口,也能被成功代理
     * 整合Apache Shiro: Shiro 的权限注解(如 @RequiresRoles, @RequiresPermissions) 需要通过 AOP 来拦截方法调用,如果没有配置 DefaultAdvisorAutoProxyCreator,这些注解将不会生效
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 为Spring-Bean开启对Shiro注解的支持
     */
    @Bean("authorizationAttributeSourceAdvisor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
