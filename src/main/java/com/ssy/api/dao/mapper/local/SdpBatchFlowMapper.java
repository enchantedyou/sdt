package com.ssy.api.dao.mapper.local;

import com.ssy.api.entity.annotation.EnableNotNull;
import com.ssy.api.entity.annotation.TableType;
import com.ssy.api.entity.table.local.SdpBatchFlow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@TableType(name = "sdp_batch_flow", desc = "batch process definition")
public interface SdpBatchFlowMapper {
	int deleteByPrimaryKey(@Param("systemCode") String systemCode, @Param("subSystemCode") String subSystemCode,
			@Param("flowGroup") String flowGroup);

	int insert(SdpBatchFlow record);

	int insertSelective(SdpBatchFlow record);

	@EnableNotNull
	SdpBatchFlow selectByPrimaryKey(@Param("systemCode") String systemCode,
			@Param("subSystemCode") String subSystemCode, @Param("flowGroup") String flowGroup, boolean nullException);

	int updateByPrimaryKeySelective(SdpBatchFlow record);

	int updateByPrimaryKey(SdpBatchFlow record);

	/** 按组查询有效的批量流程列表,通常组别按系统环境划分 **/
	List<SdpBatchFlow> selectAll(@Param("flowGroup") String flowGroup, boolean nullException);
}