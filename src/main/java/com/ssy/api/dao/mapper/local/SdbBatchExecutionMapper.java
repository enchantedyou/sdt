package com.ssy.api.dao.mapper.local;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssy.api.entity.annotation.SelectPageWithCount;
import com.ssy.api.entity.annotation.TableType;
import com.ssy.api.entity.table.local.SdbBatchExecution;

@TableType(name = "sdb_batch_execution", desc = "batch execution book")
public interface SdbBatchExecutionMapper {
	int deleteByPrimaryKey(@Param("busiOrgId") String busiOrgId, @Param("tranFlowId") String tranFlowId,
			@Param("batchRunNo") String batchRunNo);

	int insert(SdbBatchExecution record);

	int insertSelective(SdbBatchExecution record);

	SdbBatchExecution selectByPrimaryKey(@Param("busiOrgId") String busiOrgId, @Param("tranFlowId") String tranFlowId,
			@Param("batchRunNo") String batchRunNo);

	int updateByPrimaryKeySelective(SdbBatchExecution record);

	int updateByPrimaryKey(SdbBatchExecution record);

	/**
	 * 倒序查询批量执行登记簿列表
	 **/
	@SelectPageWithCount
	List<SdbBatchExecution> selectAll();
}