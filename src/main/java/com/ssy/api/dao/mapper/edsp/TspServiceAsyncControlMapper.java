package com.ssy.api.dao.mapper.edsp;

import com.ssy.api.entity.table.edsp.TspServiceAsyncControl;
import org.apache.ibatis.annotations.Param;

public interface TspServiceAsyncControlMapper {
    int deleteByPrimaryKey(@Param("systemCode") String systemCode, @Param("corpoCode") String corpoCode, @Param("innerServiceCode") String innerServiceCode);

    int insert(TspServiceAsyncControl record);

    int insertSelective(TspServiceAsyncControl record);

    TspServiceAsyncControl selectByPrimaryKey(@Param("systemCode") String systemCode, @Param("corpoCode") String corpoCode, @Param("innerServiceCode") String innerServiceCode);

    int updateByPrimaryKeySelective(TspServiceAsyncControl record);

    int updateByPrimaryKey(TspServiceAsyncControl record);
}