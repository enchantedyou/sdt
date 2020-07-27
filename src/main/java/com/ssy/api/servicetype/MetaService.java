package com.ssy.api.servicetype;

import com.github.pagehelper.PageInfo;
import com.ssy.api.entity.type.local.SdSearchDictOut;
import com.ssy.api.meta.defaults.Element;

import java.util.List;

/**
 * @Description 元数据相关服务接口
 * @Author sunshaoyu
 * @Date 2020/7/16-13:40
 */
public interface MetaService {

    /**
     * @Description 模糊查询字典列表
     * @Author sunshaoyu
     * @Date 2020/7/16-13:39
     * @param key
     * @return com.github.pagehelper.PageInfo<com.ssy.api.entity.type.local.SdSearchDictOut>
     */
    public PageInfo<SdSearchDictOut> queryDictListFuzzy(String key);
}
