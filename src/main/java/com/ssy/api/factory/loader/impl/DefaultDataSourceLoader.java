package com.ssy.api.factory.loader.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.table.local.SdpDatasource;
import com.ssy.api.exception.SdtException;
import com.ssy.api.factory.loader.DataSourceLoader;
import com.ssy.api.plugins.DBContextHolder;
import com.ssy.api.servicetype.DataSourceService;
import com.ssy.api.utils.system.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

            //数据源有效性检查
            //testDruidDataSourceValid(db.getDatasourceId(), druidDataSource);
            addDataSource(db.getDatasourceId(), druidDataSource);
            log.info("Load dynamic data source [{}-{}] successfully", db.getDatasourceId(), db.getDatasourceDesc());
        }
    }

    /**
     * @Description 数据源有效性检查
     * @Author sunshaoyu
     * @Date 2020/9/20-17:13
     * @param dataSourceId
     * @param druidDataSource
     */
    private void testDruidDataSourceValid(String dataSourceId, DruidDataSource druidDataSource){
        Connection connection = null;
        try{
            Class.forName(druidDataSource.getDriverClassName());
            connection = DriverManager.getConnection(druidDataSource.getUrl(), druidDataSource.getUsername(), druidDataSource.getPassword());
        }catch (Exception e){
            throw new SdtException("Failed to create the data source connection for ["+ dataSourceId +"],cause by: " + e.getMessage());
        }finally {
            if(null != connection){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new SdtException("Failed to close the data source connection", e);
                }
            }
        }
    }
}