package com.ssy.api.dao.mapper.local;

import com.ssy.api.entity.table.local.SdpDictPriorty;

import java.util.List;

public interface SdpDictPriortyMapper {
    int deleteByPrimaryKey(String dictType);

    int insert(SdpDictPriorty record);

    int insertSelective(SdpDictPriorty record);

    SdpDictPriorty selectByPrimaryKey(String dictType);

    int updateByPrimaryKeySelective(SdpDictPriorty record);

    int updateByPrimaryKey(SdpDictPriorty record);

    //查询有效的字典优先级列表
    List<SdpDictPriorty> selectAll_odb1();
}