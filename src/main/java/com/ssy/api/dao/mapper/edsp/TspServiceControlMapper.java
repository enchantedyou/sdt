package com.ssy.api.dao.mapper.edsp;

import com.ssy.api.entity.table.edsp.TspServiceControl;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TspServiceControlMapper {
	int deleteByPrimaryKey(@Param("systemCode") String systemCode, @Param("subSystemCode") String subSystemCode,
			@Param("serviceInvokeId") String serviceInvokeId, @Param("innerServiceCode") String innerServiceCode);

	int insert(TspServiceControl record);

	int insertSelective(TspServiceControl record);

	TspServiceControl selectByPrimaryKey(@Param("systemCode") String systemCode,
			@Param("subSystemCode") String subSystemCode, @Param("serviceInvokeId") String serviceInvokeId,
			@Param("innerServiceCode") String innerServiceCode);

	int updateByPrimaryKeySelective(TspServiceControl record);

	int updateByPrimaryKey(TspServiceControl record);

	List<TspServiceControl> selectAll();
}