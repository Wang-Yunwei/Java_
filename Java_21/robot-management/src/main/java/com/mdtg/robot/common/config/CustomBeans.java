package com.mdtg.robot.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.mdtg.robot.common.toolkit.CustomUtils;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${server.port}")
    private Integer port;

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
        return args -> {
            log.info("http://{}:{}/doc.html", CustomUtils.getInetAddresses(),port);
            System.out.println("================== 【START-UP SUCCESSFUL】 ==================");
        };
    }

    /**
     * 创建并配置 RestClient Bean
     * Spring Boot 3.4 会自动根据类路径选择工厂(JDK HttpClient 优先)
     */
    @Bean
    public RestClient restClient() {
        return RestClient.builder().build();
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 1. 乐观锁插件
//        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        // 2. 防止全表更新/删除插件
//        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        // 3. 分页插件（放最后）
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

    @Bean
    public MinioClient minioClient() {

        return MinioClient.builder().endpoint(endpoint).credentials(accessKey, secretKey).build();
    }
}
