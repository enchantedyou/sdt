package com.ssy.api.dao.mapper.local;

import com.ssy.api.entity.table.local.SdbBatchExecution;
import org.apache.ibatis.annotations.Param;

public interface SdbBatchExecutionMapper {
    int deleteByPrimaryKey(@Param("busiOrgId") String busiOrgId, @Param("tranFlowId") String tranFlowId, @Param("batchRunNo") String batchRunNo);

    int insert(SdbBatchExecution record);

    int insertSelective(SdbBatchExecution record);

    SdbBatchExecution selectByPrimaryKey(@Param("busiOrgId") String busiOrgId, @Param("tranFlowId") String tranFlowId, @Param("batchRunNo") String batchRunNo);

    int updateByPrimaryKeySelective(SdbBatchExecution record);

    int updateByPrimaryKey(SdbBatchExecution record);
}