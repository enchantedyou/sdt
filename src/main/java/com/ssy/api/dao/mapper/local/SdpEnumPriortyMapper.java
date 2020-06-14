package com.ssy.api.dao.mapper.local;

import com.ssy.api.entity.table.local.SdpEnumPriorty;

import java.util.List;

public interface SdpEnumPriortyMapper {
    int deleteByPrimaryKey(String enumType);

    int insert(SdpEnumPriorty record);

    int insertSelective(SdpEnumPriorty record);

    SdpEnumPriorty selectByPrimaryKey(String enumType);

    int updateByPrimaryKeySelective(SdpEnumPriorty record);

    int updateByPrimaryKey(SdpEnumPriorty record);

    //查询有效的枚举优先级列表
    List<SdpEnumPriorty> selectAll_odb1();
}