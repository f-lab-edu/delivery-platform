package org.flab.deliveryplatform.server.config;

import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class AsyncConfiguration {

    @Value("${async-thread-pool.core-pool-size}")
    private int corePoolSize;

    @Value("${async-thread-pool.max-pool-size}")
    private int maxPoolSize;

    @Value("${async-thread-pool.queue-capacity}")
    private int queueCapacity;

    @Value("${async-thread-pool.thread-name-prefix}")
    private String threadNamePrefix;

    @Bean
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setMaxPoolSize(maxPoolSize);
        taskExecutor.setQueueCapacity(queueCapacity);
        taskExecutor.setThreadNamePrefix(threadNamePrefix);
        taskExecutor.initialize();
        return taskExecutor;
    }
}
