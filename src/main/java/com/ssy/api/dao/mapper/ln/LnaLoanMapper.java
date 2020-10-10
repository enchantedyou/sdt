package com.ssy.api.dao.mapper.ln;

import com.ssy.api.entity.annotation.TableType;
import com.ssy.api.entity.table.ln.LnaLoan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@TableType(name = "lna_loan", desc = "loan account")
public interface LnaLoanMapper {
    int deleteByPrimaryKey(@Param("loanNo") String loanNo, @Param("orgId") String orgId);

    int insert(LnaLoan record);

    int insertSelective(LnaLoan record);

    LnaLoan selectByPrimaryKey(@Param("loanNo") String loanNo, @Param("orgId") String orgId);

    int updateByPrimaryKeySelective(LnaLoan record);

    int updateByPrimaryKey(LnaLoan record);

    /** 根据法人代码查询借据列表 **/
    List<LnaLoan> selectAll_odb1(String orgId);
}