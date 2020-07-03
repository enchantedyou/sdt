package com.ssy.api.dao.mapper.edsp;

import com.ssy.api.entity.table.edsp.TspTaskExecution;
import java.util.Date;
import org.apache.ibatis.annotations.Param;

public interface TspTaskExecutionMapper {
    int deleteByPrimaryKey(@Param("taskNum") String taskNum, @Param("taskExeNum") String taskExeNum, @Param("tranDate") Date tranDate, @Param("subSystemCode") String subSystemCode, @Param("systemCode") String systemCode, @Param("corporateCode") String corporateCode);

    int insert(TspTaskExecution record);

    int insertSelective(TspTaskExecution record);

    TspTaskExecution selectByPrimaryKey(@Param("taskNum") String taskNum, @Param("taskExeNum") String taskExeNum, @Param("tranDate") Date tranDate, @Param("subSystemCode") String subSystemCode, @Param("systemCode") String systemCode, @Param("corporateCode") String corporateCode);

    int updateByPrimaryKeySelective(TspTaskExecution record);

    int updateByPrimaryKeyWithBLOBs(TspTaskExecution record);

    int updateByPrimaryKey(TspTaskExecution record);
}