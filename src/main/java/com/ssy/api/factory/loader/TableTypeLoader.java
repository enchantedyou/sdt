package com.ssy.api.factory.loader;

import com.ssy.api.meta.defaults.TableType;

import java.io.File;
import java.util.Map;

/**
 * @Description 默认表类型加载器
 * @Author sunshaoyu
 * @Date 2020/5/28-14:21
 */
public interface TableTypeLoader {

    /**
     * @Description 加载表类型
     * @Author sunshaoyu
     * @Date 2020/5/28-14:21
     * @param fileMap 文件map
     * @return java.util.Map<java.lang.String,com.ssy.api.meta.defaults.TableType>(表id, 表类型)
     */
    public Map<String, TableType> load(Map<String, File> fileMap);
}
