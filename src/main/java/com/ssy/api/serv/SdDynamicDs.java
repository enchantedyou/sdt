package com.ssy.api.serv;

import com.ssy.api.dao.mapper.local.SdpDatasourceMapper;
import com.ssy.api.entity.table.local.SdpDatasource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 动态数据源相关处理包
 * @Author sunshaoyu
 * @Date 2020年06月15日-15:39
 */
@Component
public class SdDynamicDs {

    private static SdpDatasourceMapper sdpDatasourceMapper;

    @Autowired
    public void setSdpDatasourceMapper(SdpDatasourceMapper sdpDatasourceMapper) {
        SdDynamicDs.sdpDatasourceMapper = sdpDatasourceMapper;
    }

    /**
     * @Description 查询数据源列表
     * @Author sunshaoyu
     * @Date 2020/6/15-15:44
     * @return java.util.List<com.ssy.api.entity.table.local.SdpDatasource>
     */
    public static List<SdpDatasource> queryDataSourceList() {
        return sdpDatasourceMapper.selectAll_odb1();
    }
}
