package com.ssy.api.thread;

import com.ssy.api.entity.config.SdtContextConfig;
import com.ssy.api.entity.enums.E_BATCHEXESTATUS;
import com.ssy.api.entity.table.edsp.TspTaskExecution;
import com.ssy.api.entity.table.local.SdpBatchFlow;
import com.ssy.api.entity.table.local.SdpBatchStep;
import com.ssy.api.entity.type.edsp.SdCallBatchIn;
import com.ssy.api.entity.type.edsp.SdCallBatchOut;
import com.ssy.api.exception.SdtException;
import com.ssy.api.logic.batch.SdEODBatchHelper;
import com.ssy.api.utils.CommUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Callable;

/**
 * @Description 批量任务执行线程
 * @Author sunshaoyu
 * @Date 2020年07月05日-19:54
 */
public class BatchCallable implements Callable<SdCallBatchOut> {

    @Autowired
    private SdEODBatchHelper batchHelper;
    @Autowired
    private SdtContextConfig contextConfig;

    private SdCallBatchIn callBatchIn;
    private SdpBatchFlow batchFlow;
    private SdpBatchStep batchStep;

    public BatchCallable(SdCallBatchIn callBatchIn, SdpBatchFlow batchFlow, SdpBatchStep batchStep) {
        this.callBatchIn = callBatchIn;
        this.batchFlow = batchFlow;
        this.batchStep = batchStep;
    }

    @Override
    public SdCallBatchOut call() {
        SdCallBatchOut callBatchOut = new SdCallBatchOut();
        try{
            TspTaskExecution taskExecution = batchHelper.tryCallSubSystemBatchTask(callBatchIn, batchStep, batchFlow);
            if(CommUtil.isNotNull(taskExecution)){
                callBatchOut.setSystemCode(taskExecution.getSystemCode());
                callBatchOut.setBusiOrgId(taskExecution.getCorporateCode());
                callBatchOut.setTranFlowId(taskExecution.getTranFlowId());
                callBatchOut.setTrxnDate(taskExecution.getTransactionDate());

                callBatchOut.setBatchExeStatus(E_BATCHEXESTATUS.valueOf(taskExecution.getTranState()));
                callBatchOut.setTranGroupId(taskExecution.getTranGroupId());
                callBatchOut.setErrorMessage(taskExecution.getErrorMessage());
            }else{
                callBatchOut.setSystemCode(batchFlow.getSystemCode());
                callBatchOut.setBusiOrgId(contextConfig.getBusiOrgId());
                callBatchOut.setTranFlowId(batchStep.getFlowStepId());
                callBatchOut.setTranGroupId(batchStep.getFlowStepGroup());
            }
        }catch (SdtException e){
            callBatchOut.setErrorMessage(e.getMessage());
        }
        return callBatchOut;
    }
}
