package com.ssy.api.dao.mapper.edsp;

import com.ssy.api.entity.table.edsp.TspServiceOut;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TspServiceOutMapper {
	int deleteByPrimaryKey(@Param("systemCode") String systemCode, @Param("subSystemCode") String subSystemCode,
			@Param("serviceInvokeId") String serviceInvokeId, @Param("innerServiceCode") String innerServiceCode);

	int insert(TspServiceOut record);

	int insertSelective(TspServiceOut record);

	TspServiceOut selectByPrimaryKey(@Param("systemCode") String systemCode,
			@Param("subSystemCode") String subSystemCode, @Param("serviceInvokeId") String serviceInvokeId,
			@Param("innerServiceCode") String innerServiceCode);

	int updateByPrimaryKeySelective(TspServiceOut record);

	int updateByPrimaryKey(TspServiceOut record);

	/** 根据协议编号查询列表 **/
	List<TspServiceOut> selectAll(String protocolId);
}