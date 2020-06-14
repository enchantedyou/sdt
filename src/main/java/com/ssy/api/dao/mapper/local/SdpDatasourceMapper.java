package com.ssy.api.dao.mapper.local;

import com.ssy.api.entity.table.local.SdpDatasource;
import org.apache.ibatis.annotations.Param;

public interface SdpDatasourceMapper {
    int deleteByPrimaryKey(@Param("datasourceId") String datasourceId, @Param("datasourceType") String datasourceType);

    int insert(SdpDatasource record);

    int insertSelective(SdpDatasource record);

    SdpDatasource selectByPrimaryKey(@Param("datasourceId") String datasourceId, @Param("datasourceType") String datasourceType);

    int updateByPrimaryKeySelective(SdpDatasource record);

    int updateByPrimaryKey(SdpDatasource record);
}