package com.ssy.api.dao.mapper.edsp;

import com.ssy.api.entity.table.edsp.TspFlowStepController;
import org.apache.ibatis.annotations.Param;

public interface TspFlowStepControllerMapper {
    int deleteByPrimaryKey(@Param("systemCode") String systemCode, @Param("corporateCode") String corporateCode, @Param("tranFlowId") String tranFlowId, @Param("flowStepNum") Integer flowStepNum, @Param("executionNo") Integer executionNo, @Param("tranGroupId") String tranGroupId);

    int insert(TspFlowStepController record);

    int insertSelective(TspFlowStepController record);

    TspFlowStepController selectByPrimaryKey(@Param("systemCode") String systemCode, @Param("corporateCode") String corporateCode, @Param("tranFlowId") String tranFlowId, @Param("flowStepNum") Integer flowStepNum, @Param("executionNo") Integer executionNo, @Param("tranGroupId") String tranGroupId);

    int updateByPrimaryKeySelective(TspFlowStepController record);

    int updateByPrimaryKey(TspFlowStepController record);
}