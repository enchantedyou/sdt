package com.ssy.api.dao.mapper.ap;

import com.ssy.api.entity.annotation.TableType;
import com.ssy.api.entity.table.ap.ApsAccountingEvent;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@TableType(name = "aps_accounting_event", desc = "accouting event records")
public interface ApsAccountingEventMapper {
    int deleteByPrimaryKey(@Param("trxnSeq") String trxnSeq, @Param("dataSort") Long dataSort, @Param("orgId") String orgId);

    int insert(ApsAccountingEvent record);

    int insertSelective(ApsAccountingEvent record);

    ApsAccountingEvent selectByPrimaryKey(@Param("trxnSeq") String trxnSeq, @Param("dataSort") Long dataSort, @Param("orgId") String orgId);

    int updateByPrimaryKeySelective(ApsAccountingEvent record);

    int updateByPrimaryKey(ApsAccountingEvent record);

    BigDecimal selSumAmtByBalAttribute(@Param("balAttributes")String balAttributes, @Param("acctNo")String acctNo, @Param("debitCredit")String debitCredit);

    /** 根据流水和账号获取会计事件分录 **/
    List<ApsAccountingEvent> selectAll_odb1(@Param("acctNo")String acctNo, @Param("orgId") String orgId);
}