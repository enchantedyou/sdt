package com.ssy.api.dao.mapper.local;

import com.ssy.api.entity.annotation.EnableNotNull;
import com.ssy.api.entity.annotation.TableType;
import com.ssy.api.entity.table.local.SdpDictPriorty;

import java.util.List;

@TableType(name = "sdp_dict_priorty", desc = "parameter of dict priorty")
public interface SdpDictPriortyMapper {
    int deleteByPrimaryKey(String dictType);

    int insert(SdpDictPriorty record);

    int insertSelective(SdpDictPriorty record);

    @EnableNotNull
    SdpDictPriorty selectByPrimaryKey(String dictType, boolean nullException);

    int updateByPrimaryKeySelective(SdpDictPriorty record);

    int updateByPrimaryKey(SdpDictPriorty record);

    //查询有效的字典优先级列表
    @EnableNotNull
    List<SdpDictPriorty> selectAll_odb1(boolean nullException);
}