package com.ssy.api.entity.config;

import com.ssy.api.utils.system.BizUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @Description 异步配置
 * @Author sunshaoyu
 * @Date 2020年07月17日-16:51
 */
@Configuration
@Slf4j
public class AsyncConfig implements AsyncConfigurer {

    @Autowired
    private SdtContextConfig contextConfig;

    @Override
    public Executor getAsyncExecutor() {
        return Executors.newFixedThreadPool(contextConfig.getBatchPollThreadPoolSize());
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (e, method, objs) -> {
            BizUtil.logError(e);
        };
    }
}
