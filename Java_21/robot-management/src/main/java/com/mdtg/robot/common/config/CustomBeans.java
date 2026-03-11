package com.mdtg.robot.common.config;

import com.mdtg.robot.module.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/**
 * @author WangYunwei [2026-03-09]
 */
@Slf4j
@Configuration
public class CustomBeans {

    /**
     * CommandLineRunner
     * <p>参数类型: String... args (原始字符串数组)</p>
     * <p>参数处理: 需要手动解析参数，较为原始</p>
     * <p>执行顺序: 优先执行</p>
     * <p>适用场景: 简单的启动任务，无需复杂参数解析</p>
     * <pre>
     *      public void run(String... args) throws Exception {
     *          System.out.println("CommandLineRunner 接收到的参数：");
     *          // 假如启动命令是: java -jar app.jar --env=prod data.txt
     *          // 这里输出的是原始数组: [--env=prod, data.txt]
     *          for (String arg : args) {
     *              System.out.println(arg);
     *          }
     *      }
     *  </pre>
     * ApplicationRunner
     * <p>参数类型: ApplicationArguments (封装的对象)</p>
     * <p>参数处理: 内置支持解析选项参数（如 --name=xx）和非选项参数</p>
     * <p>执行顺序: 在 CommandLineRunner 之后执行</p>
     * <p>适用场景: 需要结构化访问命令行参数的复杂场景</p>
     * <pre>
     *      public void run(ApplicationArguments args) throws Exception {
     *          System.out.println("ApplicationRunner 开始执行");
     *
     *          // 获取所有选项名 (例如: env, debug)
     *          System.out.println("选项参数: " + args.getOptionNames());
     *
     *          // 获取 --env 的值 (例如: [prod])
     *          System.out.println("环境参数: " + args.getOptionValues("env"));
     *
     *          // 获取非选项参数 (例如: [data.txt])
     *          System.out.println("非选项参数: " + args.getNonOptionArgs());
     *      }
     *  </pre>
     */
    @Bean
    ApplicationRunner startImmediatelyExecute() {
        return args -> log.info("================== 【START-UP SUCCESSFUL】 ==================");
    }

    /**
     * 创建并配置 RestClient Bean
     * Spring Boot 3.4 会自动根据类路径选择工厂(JDK HttpClient 优先)
     */
    @Bean
    public RestClient restClient() {
        return RestClient.builder().build();
    }

    /**
     * === 1. 最底层：配置密码匹配器(加密规则) ===
     * 这里的配置必须和你 encryptPassword() 工具类中的配置完全一致
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 设置算法名称 - 必须与工具类一致 (例如: SHA-256, MD5)
        matcher.setHashAlgorithmName("SHA-256");
        // 设置迭代次数 - 必须与工具类一致 (例如: 100000)
        matcher.setHashIterations(1024);
        return matcher;
    }

    /**
     * === 2. 中间层：配置 Realm，并注入密码匹配器 ===
     */
    @Bean
    public CustomRealm customRealm(UserService userService, HashedCredentialsMatcher hashedCredentialsMatcher) {
        CustomRealm customRealm = new CustomRealm(userService);
        // 将配置好的凭证匹配器设置给 Realm
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return customRealm;
    }

    /**
     * === 3. 上层：配置 SecurityManager，并注入 Realm ===
     */
    @Bean
    public DefaultSecurityManager securityManager(CustomRealm customRealm) {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        // 这里的 customRealm() 会自动注入上面配置好的 Realm
        securityManager.setRealm(customRealm);
        return securityManager;
    }

    /**
     * === 4. 其他配置：过滤器链 ===
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        // 配置 anon (匿名可访问) 的路径
        chainDefinition.addPathDefinition("/login", "anon");
        chainDefinition.addPathDefinition("/css/**", "anon");
        chainDefinition.addPathDefinition("/js/**", "anon");
        // 配置 authc (需要认证) 的路径
        chainDefinition.addPathDefinition("/**", "authc");
        return chainDefinition;
    }
}
