package com.ssy.api.dao.mapper.local;

import com.ssy.api.entity.table.local.SdpBatchDate;

public interface SdpBatchDateMapper {
    int deleteByPrimaryKey(String busiOrgId);

    int insert(SdpBatchDate record);

    int insertSelective(SdpBatchDate record);

    SdpBatchDate selectByPrimaryKey(String busiOrgId);

    int updateByPrimaryKeySelective(SdpBatchDate record);

    int updateByPrimaryKey(SdpBatchDate record);
}