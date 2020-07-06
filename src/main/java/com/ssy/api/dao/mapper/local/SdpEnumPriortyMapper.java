package com.ssy.api.dao.mapper.local;

import com.ssy.api.entity.annotation.EnableNotNull;
import com.ssy.api.entity.annotation.TableType;
import com.ssy.api.entity.table.local.SdpEnumPriorty;

import java.util.List;

@TableType(name = "sdp_enum_priorty", desc = "parameter of enum priorty")
public interface SdpEnumPriortyMapper {
    int deleteByPrimaryKey(String enumType);

    int insert(SdpEnumPriorty record);

    int insertSelective(SdpEnumPriorty record);

    @EnableNotNull
    SdpEnumPriorty selectByPrimaryKey(String enumType, boolean nullException);

    int updateByPrimaryKeySelective(SdpEnumPriorty record);

    int updateByPrimaryKey(SdpEnumPriorty record);

    @EnableNotNull
    /** 查询有效的枚举优先级列表 **/
    List<SdpEnumPriorty> selectAll_odb1(boolean nullException);
}