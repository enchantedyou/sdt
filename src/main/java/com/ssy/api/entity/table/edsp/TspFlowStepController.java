package com.ssy.api.entity.table.edsp;

public class TspFlowStepController {
    private String systemCode;

    private String corporateCode;

    private String tranFlowId;

    private Integer flowStepNum;

    private Integer executionNo;

    private String tranGroupId;

    private String flowStepName;

    private String isExecution;

    public TspFlowStepController(String systemCode, String corporateCode, String tranFlowId, Integer flowStepNum, Integer executionNo, String tranGroupId, String flowStepName, String isExecution) {
        this.systemCode = systemCode;
        this.corporateCode = corporateCode;
        this.tranFlowId = tranFlowId;
        this.flowStepNum = flowStepNum;
        this.executionNo = executionNo;
        this.tranGroupId = tranGroupId;
        this.flowStepName = flowStepName;
        this.isExecution = isExecution;
    }

    public TspFlowStepController() {
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

    public Integer getExecutionNo() {
        return executionNo;
    }

    public void setExecutionNo(Integer executionNo) {
        this.executionNo = executionNo;
    }

    public String getTranGroupId() {
        return tranGroupId;
    }

    public void setTranGroupId(String tranGroupId) {
        this.tranGroupId = tranGroupId == null ? null : tranGroupId.trim();
    }

    public String getFlowStepName() {
        return flowStepName;
    }

    public void setFlowStepName(String flowStepName) {
        this.flowStepName = flowStepName == null ? null : flowStepName.trim();
    }

    public String getIsExecution() {
        return isExecution;
    }

    public void setIsExecution(String isExecution) {
        this.isExecution = isExecution == null ? null : isExecution.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", systemCode=").append(systemCode);
        sb.append(", corporateCode=").append(corporateCode);
        sb.append(", tranFlowId=").append(tranFlowId);
        sb.append(", flowStepNum=").append(flowStepNum);
        sb.append(", executionNo=").append(executionNo);
        sb.append(", tranGroupId=").append(tranGroupId);
        sb.append(", flowStepName=").append(flowStepName);
        sb.append(", isExecution=").append(isExecution);
        sb.append("]");
        return sb.toString();
    }
}