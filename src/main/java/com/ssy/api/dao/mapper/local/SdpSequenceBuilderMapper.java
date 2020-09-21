package com.ssy.api.dao.mapper.local;

import com.ssy.api.entity.annotation.EnableNotNull;
import com.ssy.api.entity.annotation.TableType;
import com.ssy.api.entity.table.local.SdpSequenceBuilder;

@TableType(name = "sdp_sequence_builder", desc = "sequence builder")
public interface SdpSequenceBuilderMapper {
    int deleteByPrimaryKey(String seqCode);

    int insert(SdpSequenceBuilder record);

    int insertSelective(SdpSequenceBuilder record);

    @EnableNotNull
    SdpSequenceBuilder selectByPrimaryKey(String seqCode, boolean nullException);

    int updateByPrimaryKeySelective(SdpSequenceBuilder record);

    int updateByPrimaryKey(SdpSequenceBuilder record);
}