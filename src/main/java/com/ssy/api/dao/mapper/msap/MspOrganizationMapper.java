package com.ssy.api.dao.mapper.msap;

import com.ssy.api.entity.table.msap.MspOrganization;

public interface MspOrganizationMapper {
	int deleteByPrimaryKey(String busiOrgId);

	int insert(MspOrganization record);

	int insertSelective(MspOrganization record);

	MspOrganization selectByPrimaryKey(String busiOrgId);

	int updateByPrimaryKeySelective(MspOrganization record);

	int updateByPrimaryKey(MspOrganization record);

	/** 查询首个机构信息 **/
	MspOrganization selectFirst();
}