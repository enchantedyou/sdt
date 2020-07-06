package com.ssy.api.dao.mapper.edsp;

import com.ssy.api.entity.annotation.EnableNotNull;
import com.ssy.api.entity.annotation.TableType;
import com.ssy.api.entity.table.edsp.TspFlowStepController;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@TableType(name = "tsp_flow_step_controller", desc = "batch flow step controller")
public interface TspFlowStepControllerMapper {
    int deleteByPrimaryKey(@Param("systemCode") String systemCode, @Param("corporateCode") String corporateCode, @Param("tranFlowId") String tranFlowId, @Param("flowStepNum") Integer flowStepNum, @Param("executionNo") Integer executionNo, @Param("tranGroupId") String tranGroupId);

    int insert(TspFlowStepController record);

    int insertSelective(TspFlowStepController record);

    @EnableNotNull
    TspFlowStepController selectByPrimaryKey(@Param("systemCode") String systemCode, @Param("corporateCode") String corporateCode, @Param("tranFlowId") String tranFlowId, @Param("flowStepNum") Integer flowStepNum, @Param("executionNo") Integer executionNo, @Param("tranGroupId") String tranGroupId, boolean nullException);

    int updateByPrimaryKeySelective(TspFlowStepController record);

    int updateByPrimaryKey(TspFlowStepController record);

    /** 根据组号获取批量步骤控制器列表 **/
    @EnableNotNull
    List<TspFlowStepController> selectAll_odb1(@Param("tranGroupId") String tranGroupId, boolean nullException);
}