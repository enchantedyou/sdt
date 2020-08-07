package com.ssy.api.servicetype;

import com.ssy.api.entity.table.local.SdpModuleMapping;

import java.util.List;

/**
 * @Description 业务模块映射服务接口
 * @Author sunshaoyu
 * @Date 2020年07月31日-13:12
 */
public interface ModuleMapService {

    /**
     * @Description 获取单个业务模块映射
     * @Author sunshaoyu
     * @Date 2020/7/31-13:13
     * @param moduleId
     * @return com.ssy.api.entity.table.local.SdpModuleMapping
     */
    public SdpModuleMapping getModuleMapping(String moduleId);

    /**
     * @Description 查询合并业务模块列表
     * @Author sunshaoyu
     * @Date 2020/7/31-13:14
     * @return java.util.List<com.ssy.api.entity.table.local.SdpModuleMapping>
     */
    public List<SdpModuleMapping> queryMergeModuleList();

    /**
     * @Description 查询所有模块映射列表
     * @Author sunshaoyu
     * @Date 2020/8/5-14:39
     * @return java.util.List<com.ssy.api.entity.table.local.SdpModuleMapping>
     */
    public List<SdpModuleMapping> queryAllModuleList();

    /**
     * @Description 根据内部服务码获取外部服务码
     * @Author sunshaoyu
     * @Date 2020/7/31-13:15
     * @param innerServiceCode
     * @return java.lang.String
     */
    public String getServiceCode(String innerServiceCode);
}
