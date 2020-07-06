package com.ssy.api.factory.loader;

import com.alibaba.druid.pool.DruidDataSource;

import java.util.Map;

/**
 * @Description 数据源加载器
 * @Author sunshaoyu
 * @Date 2020/7/3-13:04
 */
public interface DataSourceLoader {

    /**
     * @Description 初始化动态数据源
     * @Author sunshaoyu
     * @Date 2020/7/3-18:38
     * @return java.util.Map<java.lang.Object,java.lang.Object> <数据源的key, 数据源对象>
     */
    public Map<Object, Object> initDynamicDataSource();

    /**
     * @Description 刷新数据源
     * @Author sunshaoyu
     * @Date 2020/6/15-15:26
     */
    public void flushDataSource();

    /**
     * @Description 添加数据源
     * @Author sunshaoyu
     * @Date 2020/6/15-15:27
     * @param key
     * @param dataSource
     */
    public void addDataSource(String key, DruidDataSource dataSource);


    /**
     * @Description 从数据库、配置文件等途径加载数据源
     * @Author sunshaoyu
     * @Date 2020/6/15-15:47
     */
    public void loadDataSource();
}
