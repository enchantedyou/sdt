package com.ssy.api.factory.loader.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.table.local.SdpDatasource;
import com.ssy.api.factory.loader.DataSourceLoader;
import com.ssy.api.plugins.DBContextHolder;
import com.ssy.api.servicetype.DataSourceService;
import com.ssy.api.utils.system.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 默认数据源加载器
 * @Author sunshaoyu
 * @Date 2020年07月03日-13:09
 */
@Component
@Slf4j
public class DefaultDataSourceLoader implements DataSourceLoader {

    /** 数据源容器,存放所有的数据源 **/
    private final Map<Object, Object> dataSourceMap = new ConcurrentHashMap<>();
    @Autowired
    private DataSourceService dataSourceService;

    @Override
    public Map<Object, Object> initDynamicDataSource() {
        //初始化主数据源
        DruidDataSource masterDataSource = (DruidDataSource) SpringContextUtil.getBean(SdtConst.MASTER_DATASOURCE);
        addDataSource(SdtConst.MASTER_DATASOURCE, masterDataSource);
        //加载化其他数据源
        loadDataSource();

        //刷新数据源
        flushDataSource();
        return dataSourceMap;
    }

    @Override
    public void flushDataSource() {
        DBContextHolder dynamicDataSource = (DBContextHolder) SpringContextUtil.getBean(SdtConst.DYNAMIC_DATASOURCE);
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        dynamicDataSource.afterPropertiesSet();
    }

    @Override
    public void addDataSource(String key, DruidDataSource dataSource) {
        dataSourceMap.put(key, dataSource);
    }

    @Override
    public void loadDataSource() {
        List<SdpDatasource> datasourceList = dataSourceService.queryDataSourceList();
        for(SdpDatasource db : datasourceList){
            DruidDataSource druidDataSource = new DruidDataSource();

            druidDataSource.setUsername(db.getDatasourceUser());
            druidDataSource.setPassword(db.getDatasourcePwd());
            druidDataSource.setDriverClassName(db.getDatasourceDriver());
            druidDataSource.setUrl(db.getDatasourceUrl());
            addDataSource(db.getDatasourceId(), druidDataSource);
            log.info("Load dynamic data source {{}-{}}", db.getDatasourceId(), db.getDatasourceDesc());
        }
    }
}
