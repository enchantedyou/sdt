package com.ssy.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
@MapperScan("com.ssy.api.dao.mapper")
@EnableAsync
@EnableScheduling
public class SdtApplication extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SdtApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SdtApplication.class, args);
    }

}