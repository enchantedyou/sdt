package com.ssy.api.dao.mapper.local;

import com.ssy.api.entity.annotation.EnableNotNull;
import com.ssy.api.entity.annotation.TableType;
import com.ssy.api.entity.table.local.SdpBatchStep;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@TableType(name = "sdp_batch_step", desc = "parameter of batch step")
public interface SdpBatchStepMapper {
    int deleteByPrimaryKey(@Param("flowStepId") String flowStepId, @Param("flowStepGroup") String flowStepGroup);

    int insert(SdpBatchStep record);

    int insertSelective(SdpBatchStep record);

    SdpBatchStep selectByPrimaryKey(@Param("flowStepId") String flowStepId, @Param("flowStepGroup") String flowStepGroup);

    int updateByPrimaryKeySelective(SdpBatchStep record);

    int updateByPrimaryKey(SdpBatchStep record);

    /** 根据批量步骤组别升序查询全部 **/
    @EnableNotNull
    List<SdpBatchStep> selectAll_odb1(boolean nullException);
}