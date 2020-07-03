package com.ssy.api.dao.mapper.edsp;

import com.ssy.api.entity.table.edsp.TspTranController;
import org.apache.ibatis.annotations.Param;

public interface TspTranControllerMapper {
    int deleteByPrimaryKey(@Param("systemCode") String systemCode, @Param("corporateCode") String corporateCode, @Param("tranGroupId") String tranGroupId, @Param("stepId") Integer stepId, @Param("tranCode") String tranCode);

    int insert(TspTranController record);

    int insertSelective(TspTranController record);

    TspTranController selectByPrimaryKey(@Param("systemCode") String systemCode, @Param("corporateCode") String corporateCode, @Param("tranGroupId") String tranGroupId, @Param("stepId") Integer stepId, @Param("tranCode") String tranCode);

    int updateByPrimaryKeySelective(TspTranController record);

    int updateByPrimaryKeyWithBLOBs(TspTranController record);

    int updateByPrimaryKey(TspTranController record);
}