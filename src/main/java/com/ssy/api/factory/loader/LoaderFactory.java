package com.ssy.api.factory.loader;

/**
 * @Description 加载器工厂
 * @Author sunshaoyu
 * @Date 2020年05月26日-15:59
 */
public interface LoaderFactory {

    //默认文件加载器
    public FileLoader getFileLoader();

    //默认限制类型加载器
    public RestrictionLoader getRestrictionLoader();

    //默认复合类型加载器
    public ComplexTypeLoader getComplexTypeLoader();

    //默认表类型加载器
    public TableTypeLoader getTableTypeLoader();

    //默认数据源加载器
    public DataSourceLoader getDataSourceLoader();
}
