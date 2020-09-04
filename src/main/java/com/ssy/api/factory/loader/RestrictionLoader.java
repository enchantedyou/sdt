package com.ssy.api.factory.loader;

import com.ssy.api.entity.table.local.SdpEnumPriorty;
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

    /**
     * @Description 枚举优先级校验
     * @Author sunshaoyu
     * @Date 2020/9/3-19:28
     * @param priority
     * @param before
     * @param now
     * @return com.ssy.api.meta.abstracts.AbstractRestrictionType
     */
    public AbstractRestrictionType checkEnumPriorty(Map<String, SdpEnumPriorty> priority, AbstractRestrictionType before, AbstractRestrictionType now);
}