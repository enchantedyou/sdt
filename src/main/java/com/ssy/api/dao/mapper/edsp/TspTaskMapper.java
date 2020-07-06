package com.ssy.api.dao.mapper.edsp;

import com.ssy.api.entity.annotation.EnableNotNull;
import com.ssy.api.entity.annotation.TableType;
import com.ssy.api.entity.table.edsp.TspTask;
import org.apache.ibatis.annotations.Param;

@TableType(name = "tsp_task", desc = "batch task")
public interface TspTaskMapper {
    int deleteByPrimaryKey(@Param("subSystemCode") String subSystemCode, @Param("taskNum") String taskNum, @Param("systemCode") String systemCode, @Param("corporateCode") String corporateCode);

    int insert(TspTask record);

    int insertSelective(TspTask record);

    TspTask selectByPrimaryKey(@Param("subSystemCode") String subSystemCode, @Param("taskNum") String taskNum, @Param("systemCode") String systemCode, @Param("corporateCode") String corporateCode);

    int updateByPrimaryKeySelective(TspTask record);

    int updateByPrimaryKeyWithBLOBs(TspTask record);

    int updateByPrimaryKey(TspTask record);

    @EnableNotNull
    TspTask selectOne_odb1(@Param("transactionDate") String transactionDate, boolean nullException);

    @EnableNotNull
    TspTask selectOne_odb2(@Param("taskNum") String taskNum, boolean nullException);
}