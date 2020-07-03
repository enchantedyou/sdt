package com.ssy.api.entity.table.edsp;

public class TspTranController {
    private String systemCode;

    private String corporateCode;

    private String tranGroupId;

    private Integer stepId;

    private String tranCode;

    private String tranChineseName;

    private String executionCode;

    private String relyTranList;

    private String tranRunConditions;

    private Integer reconnectionNum;

    private String failInterruptCode;

    private Integer transactionsSubmitNum;

    private String dataSplitMode;

    private String jobExecutionMode;

    private Integer maxJobConcurrencyNum;

    private Integer logLevel;

    private String jobSplitCondition;

    private String taskRunMode;

    private String isSkip;

    private String isBatchFile;

    private String tranType;

    private String dataSplitKey;

    public TspTranController(String systemCode, String corporateCode, String tranGroupId, Integer stepId, String tranCode, String tranChineseName, String executionCode, String relyTranList, String tranRunConditions, Integer reconnectionNum, String failInterruptCode, Integer transactionsSubmitNum, String dataSplitMode, String jobExecutionMode, Integer maxJobConcurrencyNum, Integer logLevel, String jobSplitCondition, String taskRunMode, String isSkip, String isBatchFile, String tranType) {
        this.systemCode = systemCode;
        this.corporateCode = corporateCode;
        this.tranGroupId = tranGroupId;
        this.stepId = stepId;
        this.tranCode = tranCode;
        this.tranChineseName = tranChineseName;
        this.executionCode = executionCode;
        this.relyTranList = relyTranList;
        this.tranRunConditions = tranRunConditions;
        this.reconnectionNum = reconnectionNum;
        this.failInterruptCode = failInterruptCode;
        this.transactionsSubmitNum = transactionsSubmitNum;
        this.dataSplitMode = dataSplitMode;
        this.jobExecutionMode = jobExecutionMode;
        this.maxJobConcurrencyNum = maxJobConcurrencyNum;
        this.logLevel = logLevel;
        this.jobSplitCondition = jobSplitCondition;
        this.taskRunMode = taskRunMode;
        this.isSkip = isSkip;
        this.isBatchFile = isBatchFile;
        this.tranType = tranType;
    }

    public TspTranController(String systemCode, String corporateCode, String tranGroupId, Integer stepId, String tranCode, String tranChineseName, String executionCode, String relyTranList, String tranRunConditions, Integer reconnectionNum, String failInterruptCode, Integer transactionsSubmitNum, String dataSplitMode, String jobExecutionMode, Integer maxJobConcurrencyNum, Integer logLevel, String jobSplitCondition, String taskRunMode, String isSkip, String isBatchFile, String tranType, String dataSplitKey) {
        this.systemCode = systemCode;
        this.corporateCode = corporateCode;
        this.tranGroupId = tranGroupId;
        this.stepId = stepId;
        this.tranCode = tranCode;
        this.tranChineseName = tranChineseName;
        this.executionCode = executionCode;
        this.relyTranList = relyTranList;
        this.tranRunConditions = tranRunConditions;
        this.reconnectionNum = reconnectionNum;
        this.failInterruptCode = failInterruptCode;
        this.transactionsSubmitNum = transactionsSubmitNum;
        this.dataSplitMode = dataSplitMode;
        this.jobExecutionMode = jobExecutionMode;
        this.maxJobConcurrencyNum = maxJobConcurrencyNum;
        this.logLevel = logLevel;
        this.jobSplitCondition = jobSplitCondition;
        this.taskRunMode = taskRunMode;
        this.isSkip = isSkip;
        this.isBatchFile = isBatchFile;
        this.tranType = tranType;
        this.dataSplitKey = dataSplitKey;
    }

    public TspTranController() {
        super();
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

    public String getTranGroupId() {
        return tranGroupId;
    }

    public void setTranGroupId(String tranGroupId) {
        this.tranGroupId = tranGroupId == null ? null : tranGroupId.trim();
    }

    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }

    public String getTranCode() {
        return tranCode;
    }

    public void setTranCode(String tranCode) {
        this.tranCode = tranCode == null ? null : tranCode.trim();
    }

    public String getTranChineseName() {
        return tranChineseName;
    }

    public void setTranChineseName(String tranChineseName) {
        this.tranChineseName = tranChineseName == null ? null : tranChineseName.trim();
    }

    public String getExecutionCode() {
        return executionCode;
    }

    public void setExecutionCode(String executionCode) {
        this.executionCode = executionCode == null ? null : executionCode.trim();
    }

    public String getRelyTranList() {
        return relyTranList;
    }

    public void setRelyTranList(String relyTranList) {
        this.relyTranList = relyTranList == null ? null : relyTranList.trim();
    }

    public String getTranRunConditions() {
        return tranRunConditions;
    }

    public void setTranRunConditions(String tranRunConditions) {
        this.tranRunConditions = tranRunConditions == null ? null : tranRunConditions.trim();
    }

    public Integer getReconnectionNum() {
        return reconnectionNum;
    }

    public void setReconnectionNum(Integer reconnectionNum) {
        this.reconnectionNum = reconnectionNum;
    }

    public String getFailInterruptCode() {
        return failInterruptCode;
    }

    public void setFailInterruptCode(String failInterruptCode) {
        this.failInterruptCode = failInterruptCode == null ? null : failInterruptCode.trim();
    }

    public Integer getTransactionsSubmitNum() {
        return transactionsSubmitNum;
    }

    public void setTransactionsSubmitNum(Integer transactionsSubmitNum) {
        this.transactionsSubmitNum = transactionsSubmitNum;
    }

    public String getDataSplitMode() {
        return dataSplitMode;
    }

    public void setDataSplitMode(String dataSplitMode) {
        this.dataSplitMode = dataSplitMode == null ? null : dataSplitMode.trim();
    }

    public String getJobExecutionMode() {
        return jobExecutionMode;
    }

    public void setJobExecutionMode(String jobExecutionMode) {
        this.jobExecutionMode = jobExecutionMode == null ? null : jobExecutionMode.trim();
    }

    public Integer getMaxJobConcurrencyNum() {
        return maxJobConcurrencyNum;
    }

    public void setMaxJobConcurrencyNum(Integer maxJobConcurrencyNum) {
        this.maxJobConcurrencyNum = maxJobConcurrencyNum;
    }

    public Integer getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(Integer logLevel) {
        this.logLevel = logLevel;
    }

    public String getJobSplitCondition() {
        return jobSplitCondition;
    }

    public void setJobSplitCondition(String jobSplitCondition) {
        this.jobSplitCondition = jobSplitCondition == null ? null : jobSplitCondition.trim();
    }

    public String getTaskRunMode() {
        return taskRunMode;
    }

    public void setTaskRunMode(String taskRunMode) {
        this.taskRunMode = taskRunMode == null ? null : taskRunMode.trim();
    }

    public String getIsSkip() {
        return isSkip;
    }

    public void setIsSkip(String isSkip) {
        this.isSkip = isSkip == null ? null : isSkip.trim();
    }

    public String getIsBatchFile() {
        return isBatchFile;
    }

    public void setIsBatchFile(String isBatchFile) {
        this.isBatchFile = isBatchFile == null ? null : isBatchFile.trim();
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType == null ? null : tranType.trim();
    }

    public String getDataSplitKey() {
        return dataSplitKey;
    }

    public void setDataSplitKey(String dataSplitKey) {
        this.dataSplitKey = dataSplitKey == null ? null : dataSplitKey.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", systemCode=").append(systemCode);
        sb.append(", corporateCode=").append(corporateCode);
        sb.append(", tranGroupId=").append(tranGroupId);
        sb.append(", stepId=").append(stepId);
        sb.append(", tranCode=").append(tranCode);
        sb.append(", tranChineseName=").append(tranChineseName);
        sb.append(", executionCode=").append(executionCode);
        sb.append(", relyTranList=").append(relyTranList);
        sb.append(", tranRunConditions=").append(tranRunConditions);
        sb.append(", reconnectionNum=").append(reconnectionNum);
        sb.append(", failInterruptCode=").append(failInterruptCode);
        sb.append(", transactionsSubmitNum=").append(transactionsSubmitNum);
        sb.append(", dataSplitMode=").append(dataSplitMode);
        sb.append(", jobExecutionMode=").append(jobExecutionMode);
        sb.append(", maxJobConcurrencyNum=").append(maxJobConcurrencyNum);
        sb.append(", logLevel=").append(logLevel);
        sb.append(", jobSplitCondition=").append(jobSplitCondition);
        sb.append(", taskRunMode=").append(taskRunMode);
        sb.append(", isSkip=").append(isSkip);
        sb.append(", isBatchFile=").append(isBatchFile);
        sb.append(", tranType=").append(tranType);
        sb.append(", dataSplitKey=").append(dataSplitKey);
        sb.append("]");
        return sb.toString();
    }
}