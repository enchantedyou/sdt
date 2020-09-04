package com.ssy.api.factory.loader;

import com.ssy.api.entity.table.local.SdpDictPriorty;
import com.ssy.api.meta.defaults.ComplexType;
import com.ssy.api.meta.defaults.Element;

import java.io.File;
import java.util.Map;

/**
 * @Description 默认复合类型加载器
 * @Author sunshaoyu
 * @Date 2020/5/28-14:00
 */
public interface ComplexTypeLoader {

    /**
     * @Description 加载复合类型
     * @Author sunshaoyu
     * @Date 2020/6/13-15:18
     * @param fileMap   文件map
     * @return java.util.Map<java.lang.String,java.util.Map<java.lang.String,com.ssy.api.meta.defaults.ComplexType>>
     *     (模型位置, <复合类型id, 复合类型实体>)
     */
    public Map<String, Map<String, ComplexType>> load(Map<String, File> fileMap);

    /**
     * @Description 字典优先级检查
     * @Author sunshaoyu
     * @Date 2020/9/3-19:27
     * @param priority
     * @param before
     * @param now
     * @return com.ssy.api.meta.defaults.Element
     */
    public Element checkDictPriorty(Map<String, SdpDictPriorty> priority, Element before, Element now);
}
