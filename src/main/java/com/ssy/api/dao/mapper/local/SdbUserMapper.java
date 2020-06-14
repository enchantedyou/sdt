package com.ssy.api.dao.mapper.local;

import com.ssy.api.entity.table.local.SdbUser;

public interface SdbUserMapper {
    int deleteByPrimaryKey(String userAcct);

    int insert(SdbUser record);

    int insertSelective(SdbUser record);

    SdbUser selectByPrimaryKey(String userAcct);

    int updateByPrimaryKeySelective(SdbUser record);

    int updateByPrimaryKey(SdbUser record);
}