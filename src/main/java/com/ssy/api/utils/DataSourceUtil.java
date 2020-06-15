package com.ssy.api.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.table.local.SdpDatasource;
import com.ssy.api.plugins.DynamicDataSource;
import com.ssy.api.servicetype.DataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description
 * @Author sunshaoyu
 * @Date 2020年06月15日-14:48
 */
@Slf4j
@Component
public class DataSourceUtil {

    /** 数据源容器,存放所有的数据源 **/
    public static final Map<Object, Object> dataSourceMap = new ConcurrentHashMap<>();
    private static DataSourceService dataSourceService;

    @Autowired
    public void setDataSourceService(DataSourceService dataSourceService) {
        DataSourceUtil.dataSourceService = dataSourceService;
    }

    /**
     * @Description 初始化动态数据源
     * @Author sunshaoyu
     * @Date 2020/6/15-15:27
     */
    public static void initDynamicDataSource() {
        //初始化主数据源
        DruidDataSource masterDataSource = (DruidDataSource) SpringContextUtil.getBean(SdtConst.MASTER_DATASOURCE);
        addDataSource(SdtConst.MASTER_DATASOURCE, masterDataSource);
        //初始化其他数据源
        initOthersDataSource();

        //刷新数据源
        flushDataSource();
    }

    /**
     * @Description 刷新数据源
     * @Author sunshaoyu
     * @Date 2020/6/15-15:26
     */
    public static void flushDataSource() {
        DynamicDataSource dynamicDataSource = (DynamicDataSource) SpringContextUtil.getBean(SdtConst.DYNAMIC_DATASOURCE);
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        dynamicDataSource.afterPropertiesSet();
    }

    /**
     * @Description 添加数据源
     * @Author sunshaoyu
     * @Date 2020/6/15-15:27
     * @param key
     * @param dataSource
     */
    public static void addDataSource(String key, DruidDataSource dataSource) {
        dataSourceMap.put(key, dataSource);
    }

    /**
     * @Description 初始化其他数据源
     * @Author sunshaoyu
     * @Date 2020/6/15-15:47
     */
    private static void initOthersDataSource() {
        List<SdpDatasource> datasourceList = dataSourceService.queryDataSourceList();
        for(SdpDatasource db : datasourceList){
            DruidDataSource druidDataSource = new DruidDataSource();

            druidDataSource.setUsername(db.getDatasourceUser());
            druidDataSource.setPassword(db.getDatasourcePwd());
            druidDataSource.setDriverClassName(db.getDatasourceDriver());
            druidDataSource.setUrl(db.getDatasourceUrl());
            addDataSource(db.getDatasourceId(), druidDataSource);
            log.info("Initialize dynamic data source [{}]", db.getDatasourceId());
        }
    }
}
