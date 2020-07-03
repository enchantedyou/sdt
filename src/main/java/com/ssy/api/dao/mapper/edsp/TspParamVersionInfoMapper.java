package com.ssy.api.dao.mapper.edsp;

import com.ssy.api.entity.table.edsp.TspParamVersionInfo;
import org.apache.ibatis.annotations.Param;

public interface TspParamVersionInfoMapper {
    int deleteByPrimaryKey(@Param("systemCode") String systemCode, @Param("corporateCode") String corporateCode, @Param("paramCode") String paramCode);

    int insert(TspParamVersionInfo record);

    int insertSelective(TspParamVersionInfo record);

    TspParamVersionInfo selectByPrimaryKey(@Param("systemCode") String systemCode, @Param("corporateCode") String corporateCode, @Param("paramCode") String paramCode);

    int updateByPrimaryKeySelective(TspParamVersionInfo record);

    int updateByPrimaryKey(TspParamVersionInfo record);
}