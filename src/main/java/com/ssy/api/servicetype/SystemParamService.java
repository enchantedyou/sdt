package com.ssy.api.servicetype;

/**
 * @Description 系统参数服务接口
 * @Author sunshaoyu
 * @Date 2020年07月05日-17:50
 */
public interface SystemParamService {

    /**
     * @Description 根据主键和子键获取参数
     * @Author sunshaoyu
     * @Date 2020/7/5-17:51
     * @param mainKey
     * @param subKey
     * @return java.lang.String
     */
    public String getValue(String mainKey, String subKey);

    /**
     * @Description 根据主键获取参数
     * @Author sunshaoyu
     * @Date 2020/7/5-17:52
     * @param mainKey
     * @return java.lang.String
     */
    public String getValue(String mainKey);
}
