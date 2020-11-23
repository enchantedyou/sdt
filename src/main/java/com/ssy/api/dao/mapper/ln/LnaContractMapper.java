package com.ssy.api.dao.mapper.ln;

import com.ssy.api.entity.table.ln.LnaContract;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LnaContractMapper {
    int deleteByPrimaryKey(@Param("contractNo") String contractNo, @Param("orgId") String orgId);

    int insert(LnaContract record);

    int insertSelective(LnaContract record);

    LnaContract selectByPrimaryKey(@Param("contractNo") String contractNo, @Param("orgId") String orgId);

    int updateByPrimaryKeySelective(LnaContract record);

    int updateByPrimaryKey(LnaContract record);

    List<LnaContract> selectAll_odb1();
}