package com.ssy.api.dao.mapper.edsp;

import com.ssy.api.entity.annotation.EnableNotNull;
import com.ssy.api.entity.annotation.TableType;
import com.ssy.api.entity.table.edsp.TspTaskExecutionDomain;
import org.apache.ibatis.annotations.Param;

@TableType(name = "tsp_task_execution_domain", desc = "batch task execution domain")
public interface TspTaskExecutionDomainMapper {
    int deleteByPrimaryKey(@Param("systemCode") String systemCode, @Param("corporateCode") String corporateCode, @Param("tranExecutionDomainCode") String tranExecutionDomainCode);

    int insert(TspTaskExecutionDomain record);

    int insertSelective(TspTaskExecutionDomain record);

    @EnableNotNull
    TspTaskExecutionDomain selectByPrimaryKey(@Param("systemCode") String systemCode, @Param("corporateCode") String corporateCode, @Param("tranExecutionDomainCode") String tranExecutionDomainCode, boolean nullException);

    int updateByPrimaryKeySelective(TspTaskExecutionDomain record);

    int updateByPrimaryKeyWithBLOBs(TspTaskExecutionDomain record);

    int updateByPrimaryKey(TspTaskExecutionDomain record);

    /** 根据法人代码和批量任务执行域查询 **/
    @EnableNotNull
    TspTaskExecutionDomain selectOne_odb1(@Param("corporateCode") String corporateCode, @Param("tranExecutionDomainCode") String tranExecutionDomainCode, boolean nullException);

}