package com.ssy.api.logic.batch;

import com.alibaba.fastjson.JSON;
import com.ssy.api.dao.mapper.edsp.*;
import com.ssy.api.dao.mapper.local.SdbBatchExecutionMapper;
import com.ssy.api.dao.mapper.local.SdbBatchSubExecutionMapper;
import com.ssy.api.dao.mapper.local.SdpBatchFlowMapper;
import com.ssy.api.dao.mapper.local.SdpBatchStepMapper;
import com.ssy.api.entity.config.SdtContextConfig;
import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.dict.SdtDict;
import com.ssy.api.entity.enums.E_BATCHCALLMODE;
import com.ssy.api.entity.enums.E_BATCHEXESTATUS;
import com.ssy.api.entity.enums.E_ODBOPERATE;
import com.ssy.api.entity.lang.Params;
import com.ssy.api.entity.table.edsp.*;
import com.ssy.api.entity.table.local.SdbBatchExecution;
import com.ssy.api.entity.table.local.SdbBatchSubExecution;
import com.ssy.api.entity.table.local.SdpBatchFlow;
import com.ssy.api.entity.table.local.SdpBatchStep;
import com.ssy.api.entity.type.edsp.SdCallBatchIn;
import com.ssy.api.exception.SdtException;
import com.ssy.api.exception.SdtServError;
import com.ssy.api.plugins.DBContextHolder;
import com.ssy.api.plugins.SdtBatchScanner;
import com.ssy.api.servicetype.AppDateService;
import com.ssy.api.servicetype.SystemParamService;
import com.ssy.api.utils.system.BizUtil;
import com.ssy.api.utils.system.CommUtil;
import com.ssy.api.utils.business.SdtBusiUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.Callable;

/**
 * @Description
 * @Author sunshaoyu
 * @Date 2020年07月04日-18:47
 */
@Component
@Slf4j
public class SdEODBatchHelper {

    @Autowired
    private SdpBatchFlowMapper sdpBatchFlowMapper;
    @Autowired
    private SdtContextConfig contextConfig;
    @Autowired
    private SdpBatchStepMapper sdpBatchStepMapper;
    @Autowired
    private SdbBatchExecutionMapper sdbBatchExecutionMapper;
    @Autowired
    private SdbBatchSubExecutionMapper sdbBatchSubExecutionMapper;
    @Autowired
    private TspTaskExecutionDomainMapper tspTaskExecutionDomainMapper;
    @Autowired
    private TspFlowStepControllerMapper tspFlowStepControllerMapper;
    @Autowired
    private TspTaskMapper tspTaskMapper;
    @Autowired
    private TspTaskExecutionMapper tspTaskExecutionMapper;
    @Autowired
    private TspTranControllerMapper tspTranControllerMapper;

    @Autowired
    private SystemParamService systemParamService;
    @Autowired
    private AppDateService appDateService;

    /**
     * @Description 获取批量流程列表
     * @Author sunshaoyu
     * @Date 2020/7/4-19:55
     * @param flowGroup 组别
     * @return java.util.List<com.ssy.api.entity.table.local.SdpBatchFlow>
     */
    public List<SdpBatchFlow> queryBatchFlowList(String flowGroup){
        return sdpBatchFlowMapper.selectAll_odb1(flowGroup, true);
    }

    /**
     * @Description 获取批量步骤控制器列表
     * @Author sunshaoyu
     * @Date 2020/7/4-20:35
     * @return java.util.List<com.ssy.api.entity.table.edsp.TspFlowStepController>
     */
    public List<TspFlowStepController> queryFlowStepControllerList(String systemCode, String busiOrgId){
        //获取任务执行域
        TspTaskExecutionDomain executionDomain = tspTaskExecutionDomainMapper.selectByPrimaryKey(systemCode, busiOrgId, contextConfig.getEodDomainCode(), true);
        //解析批量任务组
        String tranFlowList = executionDomain.getExeTranFlowList();
        StringTokenizer tokenizer = new StringTokenizer(tranFlowList, SdtConst.EXE_GROUP_SPLIT_TOKEN);

        List<TspFlowStepController> flowStepControllerList = new ArrayList<>();
        while(tokenizer.hasMoreTokens()){
            flowStepControllerList.addAll(tspFlowStepControllerMapper.selectAll_odb1(tokenizer.nextToken(), false));
        }
        return flowStepControllerList;
    }

    /**
     * @Description 获取批量步骤列表
     * @Author sunshaoyu
     * @Date 2020/7/4-21:30
     * @return java.util.List<com.ssy.api.entity.table.local.SdpBatchStep>
     */
    public List<SdpBatchStep> queryBatchStepList(){
        return sdpBatchStepMapper.selectAll_odb1(true);
    }

    /**
     * @Description 指定日期是否存在失败或正在执行中的批量任务
     * @Author sunshaoyu
     * @Date 2020/7/4-21:00
     * @param transactionDate
     * @return boolean
     */
    private boolean isExistFailureTask(String transactionDate){
        return CommUtil.isNotNull(tspTaskMapper.selectOne_odb1(transactionDate, false));
    }

    /**
     * @Description 构建批量任务标识
     * @Author sunshaoyu
     * @Date 2020/7/5-17:16
     * @param batchStep
     * @return java.lang.String
     */
    private String buildTaskNum(SdpBatchStep batchStep) {
        return new StringBuffer(batchStep.getFlowStepId()).append("_")
                .append(appDateService.queryCurrentDate()).append("_")
                .append(contextConfig.getBusiOrgId()).toString();
    }

    /**
     * @Description 构建批量数据区json
     * @Author sunshaoyu
     * @Date 2020/7/5-18:00
     * @param currentDate
     * @param batchStep
     * @param batchFlow
     * @return java.lang.String
     */
    private String buildTaskDataArea(String currentDate, SdpBatchStep batchStep, SdpBatchFlow batchFlow){
        Params input = new Params();
        input.add("flowid", batchStep.getFlowStepId()).add("emanad", currentDate).add("corpno", contextConfig.getBusiOrgId());
        Params commReq = new Params();
        commReq.add("corporate_code", contextConfig.getBusiOrgId()).add("sponsor_system", batchFlow.getSystemCode()).add("jiaoyirq", currentDate)
                .add("channel_id", contextConfig.getBatchChannelId()).add("initiator_system", batchFlow.getSystemCode()).add("trxn_teller", systemParamService.getValue(SdtConst.DEFAULT_TELLER));

        Params sys = new Params();
        sys.add("jiaoyirq", currentDate);
        Params dataArea = new Params();
        dataArea.add("input", input).add("comm_req", commReq).add("sys", sys);
        return JSON.toJSONString(dataArea);
    }

    /**
     * @Description 唤醒批量前的检查
     * @Author sunshaoyu
     * @Date 2020/7/5-16:45
     * @param callBatchIn
     */
    private void checkBeforeCallBatch(SdCallBatchIn callBatchIn){
        BizUtil.fieldNotNull(callBatchIn.getFlowGroup(), SdtDict.A.flow_group.getId(), SdtDict.A.flow_group.getLongName());
        BizUtil.fieldNotNull(callBatchIn.getBatchCallMode(), SdtDict.A.batch_call_mode.getId(), SdtDict.A.batch_call_mode.getLongName());

        SdbBatchExecution batchExecution = sdbBatchExecutionMapper.selectByPrimaryKey(contextConfig.getBusiOrgId(), contextConfig.getEodDomainCode(), callBatchIn.getBatchRunNo());
        if(CommUtil.isNotNull(batchExecution)){
            throw SdtServError.E0008(batchExecution.getBatchRunNo(), batchExecution.getTranState());
        }

        String currentDate = appDateService.queryCurrentDate();
        if(callBatchIn.getBatchCallMode() == E_BATCHCALLMODE.DATE){
            BizUtil.fieldNotNull(callBatchIn.getEndDate(), SdtDict.A.end_date.getId(), SdtDict.A.end_date.getLongName());
            SdtBusiUtil.checkDateValid(callBatchIn.getEndDate(), SdtDict.A.end_date.getLongName());

            //结束日期必须大于当前交易日期
            if(CommUtil.compare(callBatchIn.getEndDate(), currentDate) <= 0){
                SdtServError.E0004(SdtDict.A.end_date.getLongName(), callBatchIn.getEndDate(), SdtDict.A.trxn_date.getLongName(), currentDate);
            }
        }else if(callBatchIn.getBatchCallMode() == E_BATCHCALLMODE.DAYS){
            BizUtil.fieldNotNull(callBatchIn.getAssignDays(), SdtDict.A.assign_days.getId(), SdtDict.A.assign_days.getLongName());
            SdtBusiUtil.checkIntegerValid(callBatchIn.getAssignDays(), SdtDict.A.assign_days.getLongName());
            SdtBusiUtil.checkAmountPositive(new BigDecimal(callBatchIn.getAssignDays()), SdtDict.A.assign_days.getLongName());

            final int limitDays = 1;
            if(callBatchIn.getAssignDays() != limitDays){
                SdtServError.E0007(SdtDict.A.assign_days.getLongName(), callBatchIn.getAssignDays(), limitDays);
            }
        }
    }

    /**
     * @Description 异步唤醒批量线程
     * @Author sunshaoyu
     * @Date 2020/7/5-20:50
     * @param callBatchIn
     */
    public void asyncCallBatch(SdCallBatchIn callBatchIn) {
        BizUtil.fieldNotNull(callBatchIn.getFlowGroup(), SdtDict.A.flow_group.getId(), SdtDict.A.flow_group.getLongName());
        List<SdpBatchStep> batchStepList = queryBatchStepList();
        List<SdpBatchFlow> batchFlowList = queryBatchFlowList(callBatchIn.getFlowGroup());

        for(SdpBatchStep batchStep : batchStepList){
            SdbBatchExecution batchExecution = refreshBatchExecuteion(callBatchIn, batchFlowList.get(0), batchStep);
            //对每个子系统执行当前批量组(Switch、High、Middle、General、Low、Clear、Finish)
            for(SdpBatchFlow batchFlow : batchFlowList){
                SdtBatchScanner.submitTask(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return SdEODBatchHelper.this.call(callBatchIn, batchStep, batchFlow, batchExecution);
                    }
                });
            }

            boolean result = SdtBatchScanner.checkCurrentCallResult();
            refreshBatchExecuteion(callBatchIn, batchFlowList.get(0), batchStep);
            //当前组处理失败,跳过后续组
            if(!result){
                log.info("The current batch group [{}-{}] has failed batch tasks, skip the execution of subsequent groups", batchStep.getFlowStepGroup(), batchStep.getFlowStepId());
                break;
            }
        }
    }

    /**
     * @Description 供Callable调用
     * @Author sunshaoyu
     * @Date 2020/7/7-13:25
     * @param callBatchIn
     * @param batchStep
     * @param batchFlow
     * @param batchExecution
     * @return java.lang.Boolean
     */
    private Boolean call(SdCallBatchIn callBatchIn, SdpBatchStep batchStep, SdpBatchFlow batchFlow, SdbBatchExecution batchExecution) {
        Throwable error = null;
        TspTaskExecution taskExecution = null;

        try{
            taskExecution = tryCallSubSystemBatchTask(callBatchIn, batchStep, batchFlow);
            if(CommUtil.isNotNull(taskExecution)){
                return CommUtil.equals(taskExecution.getTranState(), E_BATCHEXESTATUS.success.getValue());
            }
            return true;
        }
        catch (Exception e){
            error = e;
            throw new SdtException(e);
        }finally {
            if(!(error instanceof SdtException)){
                refreshBatchSubExecuteion(error, taskExecution, batchStep, batchFlow, batchExecution);
            }
        }
    }

    /**
     * @Description 尝试唤醒子系统的批量任务
     * @Author sunshaoyu
     * @Date 2020/7/5-19:01
     * @param callBatchIn
     * @param batchStep
     * @param batchFlow
     * @return com.ssy.api.entity.table.edsp.TspTaskExecution
     */
    protected TspTaskExecution tryCallSubSystemBatchTask(SdCallBatchIn callBatchIn, SdpBatchStep batchStep, SdpBatchFlow batchFlow) {
        //数据源编号检查
        BizUtil.fieldNotNull(batchFlow.getDatasourceId(), SdtDict.A.datasource_id.getId(), SdtDict.A.datasource_id.getLongName());
        //数据源切换并检查输入域
        DBContextHolder.switchToDataSourceForce(batchFlow.getDatasourceId());
        checkBeforeCallBatch(callBatchIn);

        if(CommUtil.equals(batchStep.getFlowStepId(), SdtConst.DAY_SWITCH_FLOW_ID)){
            //当天有失败或正在进行中的日终批量,不日切
            if(isExistFailureTask(appDateService.queryCurrentDate())){
                log.info("Date switch is not performed");
            }else if(callBatchIn.getBatchCallMode() == E_BATCHCALLMODE.DATE){
                log.info("Transaction date jumps to {}", callBatchIn.getEndDate());
                appDateService.resetCurrentDate(callBatchIn.getEndDate());
            }
        }

        List<TspTranController> tspTranControllerList = tspTranControllerMapper.selectAll_odb1(batchStep.getFlowStepGroup());
        if(CommUtil.isNotNull(tspTranControllerList)){
            //启动批量
            String taskNum = buildTaskNum(batchStep);
            TspTask tspTask = tspTaskMapper.selectOne_odb2(taskNum, false);

            TspTaskExecution taskExecution = tryStartupTask(tspTask, taskNum, batchStep, batchFlow);
            if(CommUtil.isNull(taskExecution)){
                throw SdtServError.E0006(batchFlow.getSystemCode());
            }else if(!CommUtil.equals(taskExecution.getTranState(), E_BATCHEXESTATUS.success.getValue())){
                log.info("The execution result of batch flow [{}] is [{}], and the subsequent flows are skipped", batchStep.getFlowStepId(), taskExecution.getTranState());
            }
            return taskExecution;
        }else{
            log.info("There are no executable batch transactions under the flow step [{}], so skip the flow", batchStep.getFlowStepId());
        }
        return null;
    }

    /**
     * @Description 尝试启动批量任务
     * @Author sunshaoyu
     * @Date 2020/7/5-18:15
     * @param tspTask
     * @param taskNum
     * @param batchStep
     * @param batchFlow
     * @return com.ssy.api.entity.table.edsp.TspTaskExecution
     */
    private TspTaskExecution tryStartupTask(TspTask tspTask, String taskNum, SdpBatchStep batchStep, SdpBatchFlow batchFlow) {
        String currentDate = appDateService.queryCurrentDate();

        //新增批量任务并挂起
        if(CommUtil.isNull(tspTask)) {
            tspTask = new TspTask();
            tspTask.setSystemCode(batchFlow.getSystemCode());
            tspTask.setSubSystemCode(batchFlow.getSubSystemCode());
            tspTask.setTaskNum(taskNum);

            tspTask.setTranDate(currentDate.substring(0, 4) + "-" + currentDate.substring(4, 6) + "-" + currentDate.substring(6));
            tspTask.setTransactionDate(currentDate);
            tspTask.setTranState(E_BATCHEXESTATUS.onprocess.getValue());
            tspTask.setCorporateCode(contextConfig.getBusiOrgId());

            tspTask.setDataArea(buildTaskDataArea(currentDate, batchStep, batchFlow));
            tspTask.setTaskExeNum(String.valueOf(System.currentTimeMillis()));
            tspTask.setTaskCommitDate(currentDate);
            tspTask.setTaskExeMode(SdtConst.BATCH_EXECUTE_MODE);

            tspTask.setTranFlowId(batchStep.getFlowStepId());
            tspTaskMapper.insert(tspTask);
            log.info("Add batch task [{}] and suspend", taskNum);
        }
        //不成功则重新挂起
        else if(!CommUtil.equals(tspTask.getTranState(), E_BATCHEXESTATUS.success.getValue())){
            log.info("The original state of the batch task [{}] is [{}], resuspend", taskNum, E_BATCHEXESTATUS.valueOf(tspTask.getTranDate()).getLongName());
            tspTask.setTranState(E_BATCHEXESTATUS.onprocess.getValue());
            tspTaskMapper.updateByPrimaryKey(tspTask);
        }
        //成功则直接返回
        else if(CommUtil.equals(tspTask.getTranState(), E_BATCHEXESTATUS.success.getValue())){
            log.info("The execution status of the current batch task[{}] is [{}], skip", taskNum, tspTask.getTranState());
            return CommUtil.copyToTargetObject(tspTask, TspTaskExecution.class);
        }

        //开启监听
        return batchTaskProcessListener(taskNum, batchFlow);
    }

    /**
     * @Description 监听批量任务执行过程
     * @Author sunshaoyu
     * @Date 2020/7/5-18:33
     * @param taskNum
     * @param batchFlow
     * @return com.ssy.api.entity.table.edsp.TspTaskExecution
     */
    private TspTaskExecution batchTaskProcessListener(String taskNum, SdpBatchFlow batchFlow){
        int waitCount = 0;
        for(;;){
            BizUtil.systemPause(contextConfig.getBatchPollInterval());
            TspTaskExecution taskExecution = tspTaskExecutionMapper.selectOne_odb1(taskNum, batchFlow.getSystemCode());

            if(CommUtil.isNull(taskExecution)){
                if(CommUtil.compare(++waitCount, contextConfig.getBatchMaxWaitCount()) > 0){
                    log.info("The batch task executor failed to query the result and the query times exceeded the maximum {{}}", contextConfig.getBatchMaxWaitCount());
                    break;
                }
                continue;
            }else{
                E_BATCHEXESTATUS batchEXeStatus = E_BATCHEXESTATUS.valueOf(taskExecution.getTranState());
                log.info("Batch task [{}] execution, execution status [{}], transaction date [{}],current step number [{}],current group number [{}]",
                        taskNum, batchEXeStatus.getLongName(), taskExecution.getTransactionDate(), taskExecution.getCurrentStep(), taskExecution.getCurrentTranGroupId()
                );

                if(batchEXeStatus != E_BATCHEXESTATUS.onprocess
                && batchEXeStatus != E_BATCHEXESTATUS.reonprocess
                && batchEXeStatus != E_BATCHEXESTATUS.processing){
                    return taskExecution;
                }
            }
        }
        return null;
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
    private synchronized void refreshBatchSubExecuteion(Throwable e, TspTaskExecution taskExecution, SdpBatchStep batchStep, SdpBatchFlow batchFlow, SdbBatchExecution batchExecution){
        SdbBatchSubExecution batchSubExecution = sdbBatchSubExecutionMapper.selectByPrimaryKey(batchExecution.getTrxnSeq(), batchFlow.getSystemCode(), batchStep.getFlowStepId());
        E_ODBOPERATE odbOperate = E_ODBOPERATE.UPDATE;
        //设置主键部分
        if(CommUtil.isNull(batchSubExecution)){
            batchSubExecution = new SdbBatchSubExecution();
            batchSubExecution.setTrxnSeq(batchExecution.getTrxnSeq());
            batchSubExecution.setSystemCode(batchFlow.getSystemCode());
            batchSubExecution.setFlowStepId(batchStep.getFlowStepId());
            odbOperate = E_ODBOPERATE.INSERT;
        }
        //更新非主键部分
        if(CommUtil.isNotNull(taskExecution)){
            batchSubExecution.setTrxnDate(taskExecution.getTransactionDate());
            batchSubExecution.setTranState(taskExecution.getTranState());
            batchSubExecution.setTranGroupId(taskExecution.getCurrentTranGroupId());

            if(CommUtil.equals(batchExecution.getTranState(), E_BATCHEXESTATUS.success.getValue())){
                //批量执行成功则清空错误信息
                batchSubExecution.setErrorMessage(null);
                batchSubExecution.setErrorStack(null);
            }else{
                batchSubExecution.setErrorMessage(taskExecution.getErrorMessage());
                batchSubExecution.setErrorStack(taskExecution.getErrorStack());
            }
        }else if(CommUtil.isNotNull(e)){
            batchSubExecution.setTranState(E_BATCHEXESTATUS.failure.getValue());
            batchSubExecution.setErrorMessage(CommUtil.nvl(e.getMessage(), e.toString()));
            batchSubExecution.setErrorStack(Arrays.toString(e.getStackTrace()));
            batchSubExecution.setTranGroupId(batchStep.getFlowStepGroup());
        }

        //更新至数据库
        if(odbOperate == E_ODBOPERATE.INSERT){
            sdbBatchSubExecutionMapper.insert(batchSubExecution);
        }else if(odbOperate == E_ODBOPERATE.UPDATE){
            sdbBatchSubExecutionMapper.updateByPrimaryKeyWithBLOBs(batchSubExecution);
        }
    }

    /**
     * @Description 刷新批量任务执行表
     * @Author sunshaoyu
     * @Date 2020/7/7-13:06
     * @param callBatchIn
     * @param batchFlow
     * @param batchStep
     * @return com.ssy.api.entity.table.local.SdbBatchExecution
     */
    private synchronized SdbBatchExecution refreshBatchExecuteion(SdCallBatchIn callBatchIn, SdpBatchFlow batchFlow, SdpBatchStep batchStep){
        SdbBatchExecution batchExecution = sdbBatchExecutionMapper.selectByPrimaryKey(contextConfig.getBusiOrgId(), contextConfig.getEodDomainCode(), callBatchIn.getBatchRunNo());
        if(CommUtil.isNull(batchExecution)){
            batchExecution = new SdbBatchExecution();
            batchExecution.setTrxnSeq(BizUtil.buildTrxnSeq(SdtConst.TRXN_SEQ_LENGTH));
            batchExecution.setTranFlowId(contextConfig.getEodDomainCode());
            batchExecution.setBatchRunNo(new StringBuffer(contextConfig.getEodDomainCode()).append("_").append(System.currentTimeMillis()).toString());

            batchExecution.setBusiOrgId(contextConfig.getBusiOrgId());
            //切换数据源查询当前日期
            DBContextHolder.switchToDataSourceForce(batchFlow.getDatasourceId());
            batchExecution.setDayendManageDate(appDateService.queryCurrentDate());
            batchExecution.setTranState(E_BATCHEXESTATUS.onprocess.getValue());
            batchExecution.setTranGroupId(batchStep.getFlowStepGroup());
            sdbBatchExecutionMapper.insert(batchExecution);
            callBatchIn.setBatchRunNo(batchExecution.getBatchRunNo());
        }else{
            //获取执行状态
            List<SdbBatchSubExecution> subExecutionList = sdbBatchSubExecutionMapper.selectAll_odb1(batchExecution.getTrxnSeq(), batchStep.getFlowStepId());
            if(CommUtil.isNull(subExecutionList)){
                batchExecution.setTranState(E_BATCHEXESTATUS.processing.getValue());
            }else{
                String status = E_BATCHEXESTATUS.success.getValue();
                for(SdbBatchSubExecution e : subExecutionList){
                    //未成功,跳出循环更新当前状态
                    if(CommUtil.isNotNull(e.getTranState()) && !CommUtil.equals(E_BATCHEXESTATUS.success.getValue(), e.getTranState())){
                        status = e.getTranState();
                        break;
                    }
                }
                batchExecution.setTranState(status);
            }

            batchExecution.setTranGroupId(batchStep.getFlowStepGroup());
            sdbBatchExecutionMapper.updateByPrimaryKey(batchExecution);
        }
        return batchExecution;
    }
}
