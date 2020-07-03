package com.ssy.api.dao.mapper.edsp;

import com.ssy.api.entity.table.edsp.TspTaskExecutionDomain;
import org.apache.ibatis.annotations.Param;

public interface TspTaskExecutionDomainMapper {
    int deleteByPrimaryKey(@Param("systemCode") String systemCode, @Param("corporateCode") String corporateCode, @Param("tranExecutionDomainCode") String tranExecutionDomainCode);

    int insert(TspTaskExecutionDomain record);

    int insertSelective(TspTaskExecutionDomain record);

    TspTaskExecutionDomain selectByPrimaryKey(@Param("systemCode") String systemCode, @Param("corporateCode") String corporateCode, @Param("tranExecutionDomainCode") String tranExecutionDomainCode);

    int updateByPrimaryKeySelective(TspTaskExecutionDomain record);

    int updateByPrimaryKeyWithBLOBs(TspTaskExecutionDomain record);

    int updateByPrimaryKey(TspTaskExecutionDomain record);
}