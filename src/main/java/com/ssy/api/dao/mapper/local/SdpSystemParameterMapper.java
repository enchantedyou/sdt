package com.ssy.api.dao.mapper.local;

import com.ssy.api.entity.annotation.EnableNotNull;
import com.ssy.api.entity.annotation.TableType;
import com.ssy.api.entity.table.local.SdpSystemParameter;
import org.apache.ibatis.annotations.Param;

@TableType(name = "sdp_system_parameter", desc = "sunline develop tools system parameter")
public interface SdpSystemParameterMapper {
    int deleteByPrimaryKey(@Param("mainKey") String mainKey, @Param("subKey") String subKey);

    int insert(SdpSystemParameter record);

    int insertSelective(SdpSystemParameter record);

    @EnableNotNull
    SdpSystemParameter selectByPrimaryKey(@Param("mainKey") String mainKey, @Param("subKey") String subKey, boolean nullException);

    int updateByPrimaryKeySelective(SdpSystemParameter record);

    int updateByPrimaryKey(SdpSystemParameter record);
}