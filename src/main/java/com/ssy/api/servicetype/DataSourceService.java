package com.ssy.api.servicetype;

import com.ssy.api.entity.table.local.SdpDatasource;

import java.util.List;

/**
 * @Description 数据源相关服务接口
 * @Author sunshaoyu
 * @Date 2020/6/15-15:31
 */
public interface DataSourceService {

    public List<SdpDatasource> queryDataSourceList();
}
