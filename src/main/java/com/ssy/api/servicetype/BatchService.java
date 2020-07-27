package com.ssy.api.servicetype;

import com.ssy.api.entity.table.local.SdbBatchExecution;
import com.ssy.api.entity.table.local.SdpBatchDate;

import java.util.List;

/**
 * @Description 批量服务接口
 * @Author sunshaoyu
 * @Date 2020/7/17-14:51
 */
public interface BatchService {

    public SdpBatchDate queryBatchDate();

    public List<SdbBatchExecution> queryBatchExeList();

    public void runBatch();
}
