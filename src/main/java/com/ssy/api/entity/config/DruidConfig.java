package com.ssy.api.entity.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.plugins.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description druid数据源配置
 * @Author sunshaoyu
 * @Date 2020年06月15日-14:16
 */
@Configuration
@Slf4j
public class DruidConfig {

    @Autowired
    private MybatisProperties mybatisProperties;
    @Value("${mybatis.mapper-locations}")
    private String mapperLocation;

    /**
     * @Description 创建主数据源
     * @Author sunshaoyu
     * @Date 2020/6/15-14:25
     * @return javax.sql.DataSource
     */
    @Bean(name = SdtConst.MASTER_DATASOURCE)
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    public DataSource masterDataSource() {
        log.info("Create a master data source");
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * @Description 创建动态数据源
     * @Author sunshaoyu
     * @Date 2020/6/15-15:17
     * @return javax.sql.DataSource
     */
    @Bean(name = SdtConst.DYNAMIC_DATASOURCE)
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(SdtConst.MASTER_DATASOURCE, masterDataSource());
        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;

    }

    /**
     * @Description 配置sqlSessionFactoryBean
     * @Author sunshaoyu
     * @Date 2020/6/15-14:47
     * @return org.mybatis.spring.SqlSessionFactoryBean
     */
    @Bean(value = "sqlSessionFactoryBeanDynamic")
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        sqlSessionFactoryBean.setConfiguration(mybatisProperties.getConfiguration());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(mapperLocation);
        sqlSessionFactoryBean.setMapperLocations(resources);
        return sqlSessionFactoryBean;

    }

    /**
     * @Description 动态数据源事务管理
     * @Author sunshaoyu
     * @Date 2020/6/15-14:47
     * @return org.springframework.transaction.PlatformTransactionManager
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
