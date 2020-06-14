package com.ssy.api.dao.mapper.local;

import com.ssy.api.entity.table.local.SdpSystemParameter;
import org.apache.ibatis.annotations.Param;

public interface SdpSystemParameterMapper {
    int deleteByPrimaryKey(@Param("mainKey") String mainKey, @Param("subKey") String subKey);

    int insert(SdpSystemParameter record);

    int insertSelective(SdpSystemParameter record);

    SdpSystemParameter selectByPrimaryKey(@Param("mainKey") String mainKey, @Param("subKey") String subKey);

    int updateByPrimaryKeySelective(SdpSystemParameter record);

    int updateByPrimaryKey(SdpSystemParameter record);
}