package com.ssy.api.dao.mapper.system;

import com.ssy.api.entity.table.system.DBProcess;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProcessMapper {

    public List<DBProcess> showProcessList();

    public int kill(@Param("id") String id);
}