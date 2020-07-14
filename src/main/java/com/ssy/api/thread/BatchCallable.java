package com.ssy.api.thread;

import com.ssy.api.dao.mapper.local.SdbBatchSubExecutionMapper;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.enums.E_BATCHEXESTATUS;
import com.ssy.api.entity.table.edsp.TspTaskExecution;
import com.ssy.api.entity.table.local.SdbBatchSubExecution;
import com.ssy.api.entity.table.local.SdpBatchFlow;
import com.ssy.api.entity.table.local.SdpBatchStep;
import com.ssy.api.entity.type.edsp.SdCallBatchIn;
import com.ssy.api.logic.batch.SdEODBatchHelper;
import com.ssy.api.utils.system.BizUtil;
import com.ssy.api.utils.system.CommUtil;
import com.ssy.api.utils.system.SpringContextUtil;

import java.util.Arrays;
import java.util.concurrent.Callable;

/**
 * @Description 批量任务执行线程
 * @Author sunshaoyu
 * @Date 2020年07月05日-19:54
 */
public class BatchCallable extends SdEODBatchHelper implements Callable<Boolean> {

    private SdCallBatchIn callBatchIn;
    private SdpBatchFlow batchFlow;
    private SdpBatchStep batchStep;

    public BatchCallable(SdCallBatchIn callBatchIn, SdpBatchFlow batchFlow, SdpBatchStep batchStep) {
        this.callBatchIn = callBatchIn;
        this.batchFlow = batchFlow;
        this.batchStep = batchStep;
    }

    @Override
    public Boolean call() {
        try{
            TspTaskExecution taskExecution = tryCallSubSystemBatchTask(callBatchIn, batchStep, batchFlow);
            registerBatchSubExecuteion(null, taskExecution, batchStep, batchFlow);
            if(CommUtil.isNotNull(taskExecution)){
                return CommUtil.equals(taskExecution.getTranState(), E_BATCHEXESTATUS.success.getValue());
            }
            return true;
        }catch (Exception e){
            registerBatchSubExecuteion(e, null, batchStep, batchFlow);
        }
        return false;
    }

    /**
     * @Description 登记批量允许登记簿子表
     * @Author sunshaoyu
     * @Date 2020/7/6-18:52
     * @param e
     * @param taskExecution
     * @param batchStep
     * @param batchFlow
     */
    private void registerBatchSubExecuteion(Throwable e, TspTaskExecution taskExecution, SdpBatchStep batchStep, SdpBatchFlow batchFlow){
        SdbBatchSubExecution batchSubExecution = new SdbBatchSubExecution();
        //batchSubExecution.setTrxnSeq(BizUtil.getRunEnvs().getTrxnSeq());
        batchSubExecution.setTrxnSeq(BizUtil.buildTrxnSeq(SdtConst.TRXN_SEQ_LENGTH));
        batchSubExecution.setSystemCode(batchFlow.getSystemCode());
        batchSubExecution.setFlowStepId(batchStep.getFlowStepId());

        if(CommUtil.isNotNull(taskExecution)){
            batchSubExecution.setTrxnDate(taskExecution.getTransactionDate());
            batchSubExecution.setTranState(taskExecution.getTranState());
            batchSubExecution.setErrorMessage(taskExecution.getErrorMessage());
            batchSubExecution.setErrorStack(taskExecution.getErrorStack());
            batchSubExecution.setTranGroupId(taskExecution.getTranGroupId());
        }else if(CommUtil.isNotNull(e)){
            batchSubExecution.setErrorMessage(e.getMessage());
            batchSubExecution.setErrorStack(Arrays.toString(e.getStackTrace()));
            batchSubExecution.setTranGroupId(batchStep.getFlowStepGroup());
        }
        SpringContextUtil.getBean(SdbBatchSubExecutionMapper.class).insert(batchSubExecution);
    }
}
