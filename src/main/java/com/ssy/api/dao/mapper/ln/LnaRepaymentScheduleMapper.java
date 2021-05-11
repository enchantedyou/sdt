package com.ssy.api.dao.mapper.ln;

import com.ssy.api.entity.annotation.TableType;
import com.ssy.api.entity.table.ln.LnaRepaymentSchedule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@TableType(name = "lna_repayment_schedule", desc = "loan repayment schedule")
public interface LnaRepaymentScheduleMapper {
	int deleteByPrimaryKey(@Param("loanNo") String loanNo, @Param("periodNo") Long periodNo,
			@Param("subPeriodNo") Long subPeriodNo, @Param("orgId") String orgId);

	int insert(LnaRepaymentSchedule record);

	int insertSelective(LnaRepaymentSchedule record);

	LnaRepaymentSchedule selectByPrimaryKey(@Param("loanNo") String loanNo, @Param("periodNo") Long periodNo,
			@Param("subPeriodNo") Long subPeriodNo, @Param("orgId") String orgId);

	int updateByPrimaryKeySelective(LnaRepaymentSchedule record);

	int updateByPrimaryKey(LnaRepaymentSchedule record);

	/** 全量查询还款计划 **/
	List<LnaRepaymentSchedule> selectAll();
}