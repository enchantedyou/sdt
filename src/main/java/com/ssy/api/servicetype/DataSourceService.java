package com.ssy.api.servicetype;

import com.ssy.api.entity.table.local.SdpDatasource;
import com.ssy.api.entity.type.local.SdDatasourceEdit;

import java.util.List;

/**
 * @Description 数据源相关服务接口
 * @Author sunshaoyu
 * @Date 2020/6/15-15:31
 */
public interface DataSourceService {

    /**
     * @Description 数据源列表查询
     * @Author sunshaoyu
     * @Date 2020/8/7-13:34
     * @return java.util.List<com.ssy.api.entity.table.local.SdpDatasource>
     */
    public List<SdpDatasource> queryDataSourceList();

    /**
     * @Description 数据源维护
     * @Author sunshaoyu
     * @Date 2020/8/7-13:39
     * @param datasourceEdit
     */
    public void editDataSource(SdDatasourceEdit datasourceEdit);
}
