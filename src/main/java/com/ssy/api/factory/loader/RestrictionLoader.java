package com.ssy.api.factory.loader;

import com.ssy.api.meta.abstracts.AbstractRestrictionType;

import java.io.File;
import java.util.Map;

/**
 * @Description 默认限制类型加载器
 * @Author sunshaoyu
 * @Date 2020/5/28-14:00
 */
public interface RestrictionLoader {

    /**
     * @Description 加载限制类型
     * @Author sunshaoyu
     * @Date 2020/5/28-14:13
     * @param fileMap   文件map
     * @return java.util.Map<java.lang.String,java.util.Map<java.lang.String,com.ssy.api.meta.abstracts.AbstractRestrictionType>>
     *     (模型位置, <限制类型id, 限制类型>)
     */
    public Map<String, Map<String, AbstractRestrictionType>> load(Map<String, File> fileMap);
}