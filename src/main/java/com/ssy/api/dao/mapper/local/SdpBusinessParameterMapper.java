package com.ssy.api.dao.mapper.local;

import com.ssy.api.entity.table.local.SdpBusinessParameter;
import org.apache.ibatis.annotations.Param;

public interface SdpBusinessParameterMapper {
    int deleteByPrimaryKey(@Param("mainKey") String mainKey, @Param("subKey") String subKey);

    int insert(SdpBusinessParameter record);

    int insertSelective(SdpBusinessParameter record);

    SdpBusinessParameter selectByPrimaryKey(@Param("mainKey") String mainKey, @Param("subKey") String subKey);

    int updateByPrimaryKeySelective(SdpBusinessParameter record);

    int updateByPrimaryKey(SdpBusinessParameter record);
}