package com.ssy.api.entity.table.local;

public class SdbBatchExecution {
    private String busiOrgId;

    private String tranFlowId;

    private String batchRunNo;

    private String trxnSeq;

    private String dayendManageDate;

    private String tranState;

    private String tranGroupId;

    private String dataCreateUser;

    private String dataCreateTime;

    private String dataUpdateUser;

    private String dataUpdateTime;

    private Integer dataVersion;

    public SdbBatchExecution(String busiOrgId, String tranFlowId, String batchRunNo, String trxnSeq, String dayendManageDate, String tranState, String tranGroupId, String dataCreateUser, String dataCreateTime, String dataUpdateUser, String dataUpdateTime, Integer dataVersion) {
        this.busiOrgId = busiOrgId;
        this.tranFlowId = tranFlowId;
        this.batchRunNo = batchRunNo;
        this.trxnSeq = trxnSeq;
        this.dayendManageDate = dayendManageDate;
        this.tranState = tranState;
        this.tranGroupId = tranGroupId;
        this.dataCreateUser = dataCreateUser;
        this.dataCreateTime = dataCreateTime;
        this.dataUpdateUser = dataUpdateUser;
        this.dataUpdateTime = dataUpdateTime;
        this.dataVersion = dataVersion;
    }

    public SdbBatchExecution() {
        super();
    }

    public String getBusiOrgId() {
        return busiOrgId;
    }

    public void setBusiOrgId(String busiOrgId) {
        this.busiOrgId = busiOrgId == null ? null : busiOrgId.trim();
    }

    public String getTranFlowId() {
        return tranFlowId;
    }

    public void setTranFlowId(String tranFlowId) {
        this.tranFlowId = tranFlowId == null ? null : tranFlowId.trim();
    }

    public String getBatchRunNo() {
        return batchRunNo;
    }

    public void setBatchRunNo(String batchRunNo) {
        this.batchRunNo = batchRunNo == null ? null : batchRunNo.trim();
    }

    public String getTrxnSeq() {
        return trxnSeq;
    }

    public void setTrxnSeq(String trxnSeq) {
        this.trxnSeq = trxnSeq == null ? null : trxnSeq.trim();
    }

    public String getDayendManageDate() {
        return dayendManageDate;
    }

    public void setDayendManageDate(String dayendManageDate) {
        this.dayendManageDate = dayendManageDate == null ? null : dayendManageDate.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", busiOrgId=").append(busiOrgId);
        sb.append(", tranFlowId=").append(tranFlowId);
        sb.append(", batchRunNo=").append(batchRunNo);
        sb.append(", trxnSeq=").append(trxnSeq);
        sb.append(", dayendManageDate=").append(dayendManageDate);
        sb.append(", tranState=").append(tranState);
        sb.append(", tranGroupId=").append(tranGroupId);
        sb.append(", dataCreateUser=").append(dataCreateUser);
        sb.append(", dataCreateTime=").append(dataCreateTime);
        sb.append(", dataUpdateUser=").append(dataUpdateUser);
        sb.append(", dataUpdateTime=").append(dataUpdateTime);
        sb.append(", dataVersion=").append(dataVersion);
        sb.append("]");
        return sb.toString();
    }
}