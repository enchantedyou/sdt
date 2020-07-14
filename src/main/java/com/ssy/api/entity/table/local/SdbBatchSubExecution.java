package com.ssy.api.entity.table.local;

public class SdbBatchSubExecution {
    private String trxnSeq;

    private String systemCode;

    private String flowStepId;

    private String trxnDate;

    private String tranState;

    private String tranGroupId;

    private String dataCreateUser;

    private String dataCreateTime;

    private String dataUpdateUser;

    private String dataUpdateTime;

    private Integer dataVersion;

    private String errorMessage;

    private String errorStack;

    public SdbBatchSubExecution(String trxnSeq, String systemCode, String flowStepId, String trxnDate, String tranState, String tranGroupId, String dataCreateUser, String dataCreateTime, String dataUpdateUser, String dataUpdateTime, Integer dataVersion) {
        this.trxnSeq = trxnSeq;
        this.systemCode = systemCode;
        this.flowStepId = flowStepId;
        this.trxnDate = trxnDate;
        this.tranState = tranState;
        this.tranGroupId = tranGroupId;
        this.dataCreateUser = dataCreateUser;
        this.dataCreateTime = dataCreateTime;
        this.dataUpdateUser = dataUpdateUser;
        this.dataUpdateTime = dataUpdateTime;
        this.dataVersion = dataVersion;
    }

    public SdbBatchSubExecution(String trxnSeq, String systemCode, String flowStepId, String trxnDate, String tranState, String tranGroupId, String dataCreateUser, String dataCreateTime, String dataUpdateUser, String dataUpdateTime, Integer dataVersion, String errorMessage, String errorStack) {
        this.trxnSeq = trxnSeq;
        this.systemCode = systemCode;
        this.flowStepId = flowStepId;
        this.trxnDate = trxnDate;
        this.tranState = tranState;
        this.tranGroupId = tranGroupId;
        this.dataCreateUser = dataCreateUser;
        this.dataCreateTime = dataCreateTime;
        this.dataUpdateUser = dataUpdateUser;
        this.dataUpdateTime = dataUpdateTime;
        this.dataVersion = dataVersion;
        this.errorMessage = errorMessage;
        this.errorStack = errorStack;
    }

    public SdbBatchSubExecution() {
        super();
    }

    public String getTrxnSeq() {
        return trxnSeq;
    }

    public void setTrxnSeq(String trxnSeq) {
        this.trxnSeq = trxnSeq == null ? null : trxnSeq.trim();
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode == null ? null : systemCode.trim();
    }

    public String getFlowStepId() {
        return flowStepId;
    }

    public void setFlowStepId(String flowStepId) {
        this.flowStepId = flowStepId == null ? null : flowStepId.trim();
    }

    public String getTrxnDate() {
        return trxnDate;
    }

    public void setTrxnDate(String trxnDate) {
        this.trxnDate = trxnDate == null ? null : trxnDate.trim();
    }

    public String getTranState() {
        return tranState;
    }

    public void setTranState(String tranState) {
        this.tranState = tranState == null ? null : tranState.trim();
    }

    public String getTranGroupId() {
        return tranGroupId;
    }

    public void setTranGroupId(String tranGroupId) {
        this.tranGroupId = tranGroupId == null ? null : tranGroupId.trim();
    }

    public String getDataCreateUser() {
        return dataCreateUser;
    }

    public void setDataCreateUser(String dataCreateUser) {
        this.dataCreateUser = dataCreateUser == null ? null : dataCreateUser.trim();
    }

    public String getDataCreateTime() {
        return dataCreateTime;
    }

    public void setDataCreateTime(String dataCreateTime) {
        this.dataCreateTime = dataCreateTime == null ? null : dataCreateTime.trim();
    }

    public String getDataUpdateUser() {
        return dataUpdateUser;
    }

    public void setDataUpdateUser(String dataUpdateUser) {
        this.dataUpdateUser = dataUpdateUser == null ? null : dataUpdateUser.trim();
    }

    public String getDataUpdateTime() {
        return dataUpdateTime;
    }

    public void setDataUpdateTime(String dataUpdateTime) {
        this.dataUpdateTime = dataUpdateTime == null ? null : dataUpdateTime.trim();
    }

    public Integer getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(Integer dataVersion) {
        this.dataVersion = dataVersion;
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
        sb.append(", trxnSeq=").append(trxnSeq);
        sb.append(", systemCode=").append(systemCode);
        sb.append(", flowStepId=").append(flowStepId);
        sb.append(", trxnDate=").append(trxnDate);
        sb.append(", tranState=").append(tranState);
        sb.append(", tranGroupId=").append(tranGroupId);
        sb.append(", dataCreateUser=").append(dataCreateUser);
        sb.append(", dataCreateTime=").append(dataCreateTime);
        sb.append(", dataUpdateUser=").append(dataUpdateUser);
        sb.append(", dataUpdateTime=").append(dataUpdateTime);
        sb.append(", dataVersion=").append(dataVersion);
        sb.append(", errorMessage=").append(errorMessage);
        sb.append(", errorStack=").append(errorStack);
        sb.append("]");
        return sb.toString();
    }
}