package com.ssy.api.dao.mapper.edsp;

import com.ssy.api.entity.table.edsp.TspServiceIn;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TspServiceInMapper {
	int deleteByPrimaryKey(@Param("systemCode") String systemCode, @Param("subSystemCode") String subSystemCode,
			@Param("outServiceCode") String outServiceCode);

	int insert(TspServiceIn record);

	int insertSelective(TspServiceIn record);

	TspServiceIn selectByPrimaryKey(@Param("systemCode") String systemCode,
			@Param("subSystemCode") String subSystemCode, @Param("outServiceCode") String outServiceCode);

	int updateByPrimaryKeySelective(TspServiceIn record);

	int updateByPrimaryKey(TspServiceIn record);

	/** 根据协议编号查询列表 **/
	List<TspServiceIn> selectAll(String protocolId);

	/** 根据内部服务码查询 **/
	TspServiceIn selectOne(String innerServiceCode);
}