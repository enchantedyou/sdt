package com.ssy.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.ssy.api.dao.mapper")
public class SdtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SdtApplication.class, args);
    }

}