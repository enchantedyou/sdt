package com.ssy.api.dao.mapper.local;

import com.ssy.api.entity.table.local.SdsPacket;

public interface SdsPacketMapper {
    int deleteByPrimaryKey(String trxnSeq);

    int insert(SdsPacket record);

    int insertSelective(SdsPacket record);

    SdsPacket selectByPrimaryKey(String trxnSeq);

    int updateByPrimaryKeySelective(SdsPacket record);

    int updateByPrimaryKeyWithBLOBs(SdsPacket record);

    int updateByPrimaryKey(SdsPacket record);
}