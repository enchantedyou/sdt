package com.ssy.api.serviceimpl;

import com.ssy.api.dao.mapper.local.SdbBatchExecutionMapper;
import com.ssy.api.dao.mapper.local.SdpBatchDateMapper;
import com.ssy.api.dao.mapper.local.SdpBatchFlowMapper;
import com.ssy.api.entity.config.SdtContextConfig;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.enums.E_BATCHCALLMODE;
import com.ssy.api.entity.table.ap.AppDate;
import com.ssy.api.entity.table.local.SdbBatchExecution;
import com.ssy.api.entity.table.local.SdpBatchDate;
import com.ssy.api.entity.type.edsp.SdCallBatchIn;
import com.ssy.api.logic.batch.SdEODBatchHelper;
import com.ssy.api.plugins.DBContextHolder;
import com.ssy.api.servicetype.AppDateService;
import com.ssy.api.servicetype.BatchService;
import com.ssy.api.utils.system.CommUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 批量相关服务实现
 * @Author sunshaoyu
 * @Date 2020年07月17日-14:51
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class BatchServiceImpl implements BatchService {

    @Autowired
    private SdbBatchExecutionMapper batchExecutionMapper;
    @Autowired
    private SdtContextConfig contextConfig;
    @Autowired
    private SdpBatchDateMapper batchDateMapper;
    @Autowired
    private SdEODBatchHelper batchHelper;

    @Override
    public SdpBatchDate queryBatchDate() {
        return batchDateMapper.selectByPrimaryKey(contextConfig.getBusiOrgId());
    }

    @Override
    public List<SdbBatchExecution> queryBatchExeList() {
        return batchExecutionMapper.selectAll_odb1();
    }

    @Override
    @Async
    public void runBatch() {
        SdCallBatchIn callBatchIn = new SdCallBatchIn();
        callBatchIn.setBatchCallMode(E_BATCHCALLMODE.DAYS);
        callBatchIn.setAssignDays(1);
        callBatchIn.setFlowGroup(SdtConst.FLOW_GROUP);
        batchHelper.asyncCallBatch(callBatchIn);
    }
}
