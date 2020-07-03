package com.ssy.api.entity.table.edsp;

public class TspTranGroupController {
    private String systemCode;

    private String corporateCode;

    private String tranGroupId;

    private String tranGroupDesc;

    private String taskRunConditions;

    private String taskRunCallbackService;

    public TspTranGroupController(String systemCode, String corporateCode, String tranGroupId, String tranGroupDesc, String taskRunConditions, String taskRunCallbackService) {
        this.systemCode = systemCode;
        this.corporateCode = corporateCode;
        this.tranGroupId = tranGroupId;
        this.tranGroupDesc = tranGroupDesc;
        this.taskRunConditions = taskRunConditions;
        this.taskRunCallbackService = taskRunCallbackService;
    }

    public TspTranGroupController() {
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

    public String getTranGroupDesc() {
        return tranGroupDesc;
    }

    public void setTranGroupDesc(String tranGroupDesc) {
        this.tranGroupDesc = tranGroupDesc == null ? null : tranGroupDesc.trim();
    }

    public String getTaskRunConditions() {
        return taskRunConditions;
    }

    public void setTaskRunConditions(String taskRunConditions) {
        this.taskRunConditions = taskRunConditions == null ? null : taskRunConditions.trim();
    }

    public String getTaskRunCallbackService() {
        return taskRunCallbackService;
    }

    public void setTaskRunCallbackService(String taskRunCallbackService) {
        this.taskRunCallbackService = taskRunCallbackService == null ? null : taskRunCallbackService.trim();
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
        sb.append(", tranGroupDesc=").append(tranGroupDesc);
        sb.append(", taskRunConditions=").append(taskRunConditions);
        sb.append(", taskRunCallbackService=").append(taskRunCallbackService);
        sb.append("]");
        return sb.toString();
    }
}