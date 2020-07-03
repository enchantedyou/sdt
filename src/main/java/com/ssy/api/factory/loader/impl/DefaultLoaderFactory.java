package com.ssy.api.factory.loader.impl;

import com.ssy.api.factory.loader.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description 默认的加载器工厂
 * @Author sunshaoyu
 * @Date 2020年05月28日-14:26
 */
@Component
public class DefaultLoaderFactory implements LoaderFactory {

    @Autowired
    private DefaultFileLoader defaultFileLoader;
    @Autowired
    private DefaultRestrictionLoader defaultRestrictionLoader;
    @Autowired
    private DefaultComplexTypeLoader defaultComplexTypeLoader;
    @Autowired
    private DefaultTableLoader defaultTableLoader;
    @Autowired
    private DefaultDataSourceLoader defaultDataSourceLoader;

    @Override
    public FileLoader getFileLoader() {
        return defaultFileLoader;
    }

    @Override
    public RestrictionLoader getRestrictionLoader() {
        return defaultRestrictionLoader;
    }

    @Override
    public ComplexTypeLoader getComplexTypeLoader() {
        return defaultComplexTypeLoader;
    }

    @Override
    public TableTypeLoader getTableTypeLoader() {
        return defaultTableLoader;
    }

    @Override
    public DataSourceLoader getDataSourceLoader() {
        return defaultDataSourceLoader;
    }
}
