package com.ssy.api.dao.mapper.edsp;

import com.ssy.api.entity.table.edsp.TspTask;
import org.apache.ibatis.annotations.Param;

public interface TspTaskMapper {
    int deleteByPrimaryKey(@Param("subSystemCode") String subSystemCode, @Param("taskNum") String taskNum, @Param("systemCode") String systemCode, @Param("corporateCode") String corporateCode);

    int insert(TspTask record);

    int insertSelective(TspTask record);

    TspTask selectByPrimaryKey(@Param("subSystemCode") String subSystemCode, @Param("taskNum") String taskNum, @Param("systemCode") String systemCode, @Param("corporateCode") String corporateCode);

    int updateByPrimaryKeySelective(TspTask record);

    int updateByPrimaryKeyWithBLOBs(TspTask record);

    int updateByPrimaryKey(TspTask record);
}