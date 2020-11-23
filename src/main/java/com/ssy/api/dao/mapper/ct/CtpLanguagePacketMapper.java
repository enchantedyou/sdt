package com.ssy.api.dao.mapper.ct;

import com.ssy.api.entity.table.ct.CtpLanguagePacket;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CtpLanguagePacketMapper {
    int deleteByPrimaryKey(@Param("languageResourceType") String languageResourceType, @Param("languageResourceKey") String languageResourceKey, @Param("uiLanguage") String uiLanguage);

    int insert(CtpLanguagePacket record);

    int insertSelective(CtpLanguagePacket record);

    CtpLanguagePacket selectByPrimaryKey(@Param("languageResourceType") String languageResourceType, @Param("languageResourceKey") String languageResourceKey, @Param("uiLanguage") String uiLanguage);

    int updateByPrimaryKeySelective(CtpLanguagePacket record);

    int updateByPrimaryKey(CtpLanguagePacket record);

    List<CtpLanguagePacket> selectAll_odb1();
}