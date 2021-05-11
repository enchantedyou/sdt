package com.ssy.api.dao.mapper.local;

import com.ssy.api.entity.annotation.EnableNotNull;
import com.ssy.api.entity.annotation.TableType;
import com.ssy.api.entity.table.local.SdpModuleMapping;

import java.util.List;

@TableType(name = "sdp_module_mapping", desc = "business module mapping")
public interface SdpModuleMappingMapper {
	int deleteByPrimaryKey(String moduleId);

	int insert(SdpModuleMapping record);

	int insertSelective(SdpModuleMapping record);

	@EnableNotNull
	SdpModuleMapping selectByPrimaryKey(String moduleId, boolean nullException);

	int updateByPrimaryKeySelective(SdpModuleMapping record);

	int updateByPrimaryKey(SdpModuleMapping record);

	/** 搜索合并差异文件列表的可选模块 **/
	List<SdpModuleMapping> selectMergeEnableList();

	/** 查询所有的模块映射 **/
	List<SdpModuleMapping> selectAll();
}