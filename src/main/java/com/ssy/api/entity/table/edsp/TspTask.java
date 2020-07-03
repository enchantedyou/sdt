package com.ssy.api.entity.table.edsp;

import java.util.Date;

public class TspTask {
    private String subSystemCode;

    private String taskNum;

    private String systemCode;

    private String corporateCode;

    private String taskExeNum;

    private String taskCommitDate;

    private Date tranDate;

    private String transactionDate;

    private String tranFlowId;

    private Integer flowStepNum;

    private String tranGroupId;

    private String tranId;

    private Long totalCost;

    private String tranState;

    private String taskExeMode;

    private String taskInterruptFlag;

    private String taskCommitTime;

    private Integer taskPriority;

    private String tranStartTime;

    private Long tranStartTimestamp;

    private String tranEndTime;

    private Long tranEndTimestamp;

    private String vmId;

    private String ipAddress;

    private String serverHostName;

    private Integer startFlowStepNum;

    private Integer startExecutionNo;

    private String startTranGroupId;

    private Integer startStepNum;

    private String serviceCode;

    private String dataArea;

    private String errorMessage;

    private String errorStack;

    public TspTask(String subSystemCode, String taskNum, String systemCode, String corporateCode, String taskExeNum, String taskCommitDate, Date tranDate, String transactionDate, String tranFlowId, Integer flowStepNum, String tranGroupId, String tranId, Long totalCost, String tranState, String taskExeMode, String taskInterruptFlag, String taskCommitTime, Integer taskPriority, String tranStartTime, Long tranStartTimestamp, String tranEndTime, Long tranEndTimestamp, String vmId, String ipAddress, String serverHostName, Integer startFlowStepNum, Integer startExecutionNo, String startTranGroupId, Integer startStepNum, String serviceCode) {
        this.subSystemCode = subSystemCode;
        this.taskNum = taskNum;
        this.systemCode = systemCode;
        this.corporateCode = corporateCode;
        this.taskExeNum = taskExeNum;
        this.taskCommitDate = taskCommitDate;
        this.tranDate = tranDate;
        this.transactionDate = transactionDate;
        this.tranFlowId = tranFlowId;
        this.flowStepNum = flowStepNum;
        this.tranGroupId = tranGroupId;
        this.tranId = tranId;
        this.totalCost = totalCost;
        this.tranState = tranState;
        this.taskExeMode = taskExeMode;
        this.taskInterruptFlag = taskInterruptFlag;
        this.taskCommitTime = taskCommitTime;
        this.taskPriority = taskPriority;
        this.tranStartTime = tranStartTime;
        this.tranStartTimestamp = tranStartTimestamp;
        this.tranEndTime = tranEndTime;
        this.tranEndTimestamp = tranEndTimestamp;
        this.vmId = vmId;
        this.ipAddress = ipAddress;
        this.serverHostName = serverHostName;
        this.startFlowStepNum = startFlowStepNum;
        this.startExecutionNo = startExecutionNo;
        this.startTranGroupId = startTranGroupId;
        this.startStepNum = startStepNum;
        this.serviceCode = serviceCode;
    }

    public TspTask(String subSystemCode, String taskNum, String systemCode, String corporateCode, String taskExeNum, String taskCommitDate, Date tranDate, String transactionDate, String tranFlowId, Integer flowStepNum, String tranGroupId, String tranId, Long totalCost, String tranState, String taskExeMode, String taskInterruptFlag, String taskCommitTime, Integer taskPriority, String tranStartTime, Long tranStartTimestamp, String tranEndTime, Long tranEndTimestamp, String vmId, String ipAddress, String serverHostName, Integer startFlowStepNum, Integer startExecutionNo, String startTranGroupId, Integer startStepNum, String serviceCode, String dataArea, String errorMessage, String errorStack) {
        this.subSystemCode = subSystemCode;
        this.taskNum = taskNum;
        this.systemCode = systemCode;
        this.corporateCode = corporateCode;
        this.taskExeNum = taskExeNum;
        this.taskCommitDate = taskCommitDate;
        this.tranDate = tranDate;
        this.transactionDate = transactionDate;
        this.tranFlowId = tranFlowId;
        this.flowStepNum = flowStepNum;
        this.tranGroupId = tranGroupId;
        this.tranId = tranId;
        this.totalCost = totalCost;
        this.tranState = tranState;
        this.taskExeMode = taskExeMode;
        this.taskInterruptFlag = taskInterruptFlag;
        this.taskCommitTime = taskCommitTime;
        this.taskPriority = taskPriority;
        this.tranStartTime = tranStartTime;
        this.tranStartTimestamp = tranStartTimestamp;
        this.tranEndTime = tranEndTime;
        this.tranEndTimestamp = tranEndTimestamp;
        this.vmId = vmId;
        this.ipAddress = ipAddress;
        this.serverHostName = serverHostName;
        this.startFlowStepNum = startFlowStepNum;
        this.startExecutionNo = startExecutionNo;
        this.startTranGroupId = startTranGroupId;
        this.startStepNum = startStepNum;
        this.serviceCode = serviceCode;
        this.dataArea = dataArea;
        this.errorMessage = errorMessage;
        this.errorStack = errorStack;
    }

    public TspTask() {
        super();
    }

    public String getSubSystemCode() {
        return subSystemCode;
    }

    public void setSubSystemCode(String subSystemCode) {
        this.subSystemCode = subSystemCode == null ? null : subSystemCode.trim();
    }

    public String getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(String taskNum) {
        this.taskNum = taskNum == null ? null : taskNum.trim();
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode == null ? null : systemCode.trim();
    }

    public String getCorporateCode() {
        return corporateCode;
    }

    public void setCorporateCode(String corporateCode) {
        this.corporateCode = corporateCode == null ? null : corporateCode.trim();
    }

    public String getTaskExeNum() {
        return taskExeNum;
    }

    public void setTaskExeNum(String taskExeNum) {
        this.taskExeNum = taskExeNum == null ? null : taskExeNum.trim();
    }

    public String getTaskCommitDate() {
        return taskCommitDate;
    }

    public void setTaskCommitDate(String taskCommitDate) {
        this.taskCommitDate = taskCommitDate == null ? null : taskCommitDate.trim();
    }

    public Date getTranDate() {
        return tranDate;
    }

    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate == null ? null : transactionDate.trim();
    }

    public String getTranFlowId() {
        return tranFlowId;
    }

    public void setTranFlowId(String tranFlowId) {
        this.tranFlowId = tranFlowId == null ? null : tranFlowId.trim();
    }

    public Integer getFlowStepNum() {
        return flowStepNum;
    }

    public void setFlowStepNum(Integer flowStepNum) {
        this.flowStepNum = flowStepNum;
    }

    public String getTranGroupId() {
        return tranGroupId;
    }

    public void setTranGroupId(String tranGroupId) {
        this.tranGroupId = tranGroupId == null ? null : tranGroupId.trim();
    }

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId == null ? null : tranId.trim();
    }

    public Long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Long totalCost) {
        this.totalCost = totalCost;
    }

    public String getTranState() {
        return tranState;
    }

    public void setTranState(String tranState) {
        this.tranState = tranState == null ? null : tranState.trim();
    }

    public String getTaskExeMode() {
        return taskExeMode;
    }

    public void setTaskExeMode(String taskExeMode) {
        this.taskExeMode = taskExeMode == null ? null : taskExeMode.trim();
    }

    public String getTaskInterruptFlag() {
        return taskInterruptFlag;
    }

    public void setTaskInterruptFlag(String taskInterruptFlag) {
        this.taskInterruptFlag = taskInterruptFlag == null ? null : taskInterruptFlag.trim();
    }

    public String getTaskCommitTime() {
        return taskCommitTime;
    }

    public void setTaskCommitTime(String taskCommitTime) {
        this.taskCommitTime = taskCommitTime == null ? null : taskCommitTime.trim();
    }

    public Integer getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(Integer taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getTranStartTime() {
        return tranStartTime;
    }

    public void setTranStartTime(String tranStartTime) {
        this.tranStartTime = tranStartTime == null ? null : tranStartTime.trim();
    }

    public Long getTranStartTimestamp() {
        return tranStartTimestamp;
    }

    public void setTranStartTimestamp(Long tranStartTimestamp) {
        this.tranStartTimestamp = tranStartTimestamp;
    }

    public String getTranEndTime() {
        return tranEndTime;
    }

    public void setTranEndTime(String tranEndTime) {
        this.tranEndTime = tranEndTime == null ? null : tranEndTime.trim();
    }

    public Long getTranEndTimestamp() {
        return tranEndTimestamp;
    }

    public void setTranEndTimestamp(Long tranEndTimestamp) {
        this.tranEndTimestamp = tranEndTimestamp;
    }

    public String getVmId() {
        return vmId;
    }

    public void setVmId(String vmId) {
        this.vmId = vmId == null ? null : vmId.trim();
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress == null ? null : ipAddress.trim();
    }

    public String getServerHostName() {
        return serverHostName;
    }

    public void setServerHostName(String serverHostName) {
        this.serverHostName = serverHostName == null ? null : serverHostName.trim();
    }

    public Integer getStartFlowStepNum() {
        return startFlowStepNum;
    }

    public void setStartFlowStepNum(Integer startFlowStepNum) {
        this.startFlowStepNum = startFlowStepNum;
    }

    public Integer getStartExecutionNo() {
        return startExecutionNo;
    }

    public void setStartExecutionNo(Integer startExecutionNo) {
        this.startExecutionNo = startExecutionNo;
    }

    public String getStartTranGroupId() {
        return startTranGroupId;
    }

    public void setStartTranGroupId(String startTranGroupId) {
        this.startTranGroupId = startTranGroupId == null ? null : startTranGroupId.trim();
    }

    public Integer getStartStepNum() {
        return startStepNum;
    }

    public void setStartStepNum(Integer startStepNum) {
        this.startStepNum = startStepNum;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode == null ? null : serviceCode.trim();
    }

    public String getDataArea() {
        return dataArea;
    }

    public void setDataArea(String dataArea) {
        this.dataArea = dataArea == null ? null : dataArea.trim();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage == null ? null : errorMessage.trim();
    }

    public String getErrorStack() {
        return errorStack;
    }

    public void setErrorStack(String errorStack) {
        this.errorStack = errorStack == null ? null : errorStack.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", subSystemCode=").append(subSystemCode);
        sb.append(", taskNum=").append(taskNum);
        sb.append(", systemCode=").append(systemCode);
        sb.append(", corporateCode=").append(corporateCode);
        sb.append(", taskExeNum=").append(taskExeNum);
        sb.append(", taskCommitDate=").append(taskCommitDate);
        sb.append(", tranDate=").append(tranDate);
        sb.append(", transactionDate=").append(transactionDate);
        sb.append(", tranFlowId=").append(tranFlowId);
        sb.append(", flowStepNum=").append(flowStepNum);
        sb.append(", tranGroupId=").append(tranGroupId);
        sb.append(", tranId=").append(tranId);
        sb.append(", totalCost=").append(totalCost);
        sb.append(", tranState=").append(tranState);
        sb.append(", taskExeMode=").append(taskExeMode);
        sb.append(", taskInterruptFlag=").append(taskInterruptFlag);
        sb.append(", taskCommitTime=").append(taskCommitTime);
        sb.append(", taskPriority=").append(taskPriority);
        sb.append(", tranStartTime=").append(tranStartTime);
        sb.append(", tranStartTimestamp=").append(tranStartTimestamp);
        sb.append(", tranEndTime=").append(tranEndTime);
        sb.append(", tranEndTimestamp=").append(tranEndTimestamp);
        sb.append(", vmId=").append(vmId);
        sb.append(", ipAddress=").append(ipAddress);
        sb.append(", serverHostName=").append(serverHostName);
        sb.append(", startFlowStepNum=").append(startFlowStepNum);
        sb.append(", startExecutionNo=").append(startExecutionNo);
        sb.append(", startTranGroupId=").append(startTranGroupId);
        sb.append(", startStepNum=").append(startStepNum);
        sb.append(", serviceCode=").append(serviceCode);
        sb.append(", dataArea=").append(dataArea);
        sb.append(", errorMessage=").append(errorMessage);
        sb.append(", errorStack=").append(errorStack);
        sb.append("]");
        return sb.toString();
    }
}