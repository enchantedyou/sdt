package com.ssy.api.dao.mapper.ap;

import com.ssy.api.entity.annotation.EnableNotNull;
import com.ssy.api.entity.annotation.TableType;
import com.ssy.api.entity.table.ap.AppDate;

@TableType(name = "app_date", desc = "parameter of transaction date")
public interface AppDateMapper {

    int deleteByPrimaryKey(String busiOrgId);

    int insert(AppDate record);

    int insertSelective(AppDate record);

    @EnableNotNull
    AppDate selectByPrimaryKey(String busiOrgId, boolean nullException);

    int updateByPrimaryKeySelective(AppDate record);

    int updateByPrimaryKey(AppDate record);
}