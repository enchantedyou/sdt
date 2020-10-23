package com.ssy.api.dao.mapper.local;

import com.ssy.api.entity.annotation.EnableNotNull;
import com.ssy.api.entity.annotation.TableType;
import com.ssy.api.entity.table.local.SdbUser;

@TableType(name = "sdb_user", desc = "user register booK")
public interface SdbUserMapper {
    int deleteByPrimaryKey(String userAcct);

    int insert(SdbUser record);

    int insertSelective(SdbUser record);

    @EnableNotNull
    SdbUser selectByPrimaryKey(String userAcct, boolean nullException);

    int updateByPrimaryKeySelective(SdbUser record);

    int updateByPrimaryKey(SdbUser record);
}