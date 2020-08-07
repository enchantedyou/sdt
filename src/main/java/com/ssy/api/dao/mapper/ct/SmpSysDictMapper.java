package com.ssy.api.dao.mapper.ct;

import com.ssy.api.entity.table.ct.SmpSysDict;
import org.apache.ibatis.annotations.Param;

public interface SmpSysDictMapper {
    int deleteByPrimaryKey(@Param("dictId") String dictId, @Param("dictType") String dictType);

    int insert(SmpSysDict record);

    int insertSelective(SmpSysDict record);

    SmpSysDict selectByPrimaryKey(@Param("dictType") String dictType, @Param("dictId") String dictId);

    int updateByPrimaryKeySelective(SmpSysDict record);

    int updateByPrimaryKey(SmpSysDict record);
}