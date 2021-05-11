package com.ssy.api.dao.mapper.ln;

import com.ssy.api.entity.annotation.TableType;
import com.ssy.api.entity.table.ln.LnaBalance;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@TableType(name = "lna_balance", desc = "loan balance")
public interface LnaBalanceMapper {
	int deleteByPrimaryKey(@Param("loanNo") String loanNo, @Param("balAttributes") String balAttributes,
			@Param("orgId") String orgId);

	int insert(LnaBalance record);

	int insertSelective(LnaBalance record);

	LnaBalance selectByPrimaryKey(@Param("loanNo") String loanNo, @Param("balAttributes") String balAttributes,
			@Param("orgId") String orgId);

	int updateByPrimaryKeySelective(LnaBalance record);

	int updateByPrimaryKey(LnaBalance record);

	/** 查出某个借据的所有余额 **/
	List<LnaBalance> selectAll(@Param("loanNo") String loanNo, @Param("orgId") String orgId);
}