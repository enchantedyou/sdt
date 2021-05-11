package com.ssy.api.dao.mapper.ct;

import com.ssy.api.entity.table.ct.SmpSysDictLanguage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SmpSysDictLanguageMapper {
	int deleteByPrimaryKey(@Param("dictId") String dictId, @Param("dictType") String dictType);

	int insert(SmpSysDictLanguage record);

	int insertSelective(SmpSysDictLanguage record);

	SmpSysDictLanguage selectByPrimaryKey(@Param("dictId") String dictId, @Param("dictType") String dictType);

	int updateByPrimaryKeySelective(SmpSysDictLanguage record);

	int updateByPrimaryKey(SmpSysDictLanguage record);

	List<SmpSysDictLanguage> selectAll();
}