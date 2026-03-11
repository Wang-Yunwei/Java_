package com.mdtg.robot.common.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author WangYunwei [2026-03-10]
 */
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setTaskDecorator(runnable -> {
            // 保存父线程的 Subject
            Subject subject = SecurityUtils.getSubject();
            return () -> {
                // 在子线程开始时绑定
                ThreadContext.bind(subject);
                try {
                    runnable.run();
                } finally {
                    // 执行完解绑，防止内存泄漏
                    ThreadContext.unbindSubject();
                }
            };
        });
        executor.initialize();
        return executor;
    }
}
