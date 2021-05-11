package com.ssy.api.dao.mapper.local;

import com.ssy.api.entity.table.local.SdbBatchSubExecution;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SdbBatchSubExecutionMapper {
	int deleteByPrimaryKey(@Param("trxnSeq") String trxnSeq, @Param("systemCode") String systemCode,
			@Param("flowStepId") String flowStepId);

	int insert(SdbBatchSubExecution record);

	int insertSelective(SdbBatchSubExecution record);

	SdbBatchSubExecution selectByPrimaryKey(@Param("trxnSeq") String trxnSeq, @Param("systemCode") String systemCode,
			@Param("flowStepId") String flowStepId);

	int updateByPrimaryKeySelective(SdbBatchSubExecution record);

	int updateByPrimaryKeyWithBLOBs(SdbBatchSubExecution record);

	int updateByPrimaryKey(SdbBatchSubExecution record);

	/** 查询该次日终批量该组下的所有批量执行信息 **/
	List<SdbBatchSubExecution> selectAll(@Param("trxnSeq") String trxnSeq, @Param("flowStepId") String flowStepId);
}