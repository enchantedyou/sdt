package com.ssy.api.dao.mapper.edsp;

import com.ssy.api.entity.table.edsp.TspTranGroupController;
import org.apache.ibatis.annotations.Param;

public interface TspTranGroupControllerMapper {
    int deleteByPrimaryKey(@Param("systemCode") String systemCode, @Param("corporateCode") String corporateCode, @Param("tranGroupId") String tranGroupId);

    int insert(TspTranGroupController record);

    int insertSelective(TspTranGroupController record);

    TspTranGroupController selectByPrimaryKey(@Param("systemCode") String systemCode, @Param("corporateCode") String corporateCode, @Param("tranGroupId") String tranGroupId);

    int updateByPrimaryKeySelective(TspTranGroupController record);

    int updateByPrimaryKey(TspTranGroupController record);
}