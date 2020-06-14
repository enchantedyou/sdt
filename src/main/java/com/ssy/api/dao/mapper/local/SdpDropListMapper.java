package com.ssy.api.dao.mapper.local;

import com.ssy.api.entity.table.local.SdpDropList;
import org.apache.ibatis.annotations.Param;

public interface SdpDropListMapper {
    int deleteByPrimaryKey(@Param("dropListType") String dropListType, @Param("dropListValue") String dropListValue);

    int insert(SdpDropList record);

    int insertSelective(SdpDropList record);

    SdpDropList selectByPrimaryKey(@Param("dropListType") String dropListType, @Param("dropListValue") String dropListValue);

    int updateByPrimaryKeySelective(SdpDropList record);

    int updateByPrimaryKey(SdpDropList record);
}