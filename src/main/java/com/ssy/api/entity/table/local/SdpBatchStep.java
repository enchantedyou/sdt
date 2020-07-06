package com.ssy.api.entity.table.local;

public class SdpBatchStep {
    private String flowStepId;

    private String flowStepGroup;

    private String flowStepName;

    private Boolean isEnabled;

    private String dataCreateUser;

    private String dataCreateTime;

    private String dataUpdateUser;

    private String dataUpdateTime;

    private Integer dataVersion;

    public SdpBatchStep(String flowStepId, String flowStepGroup, String flowStepName, Boolean isEnabled, String dataCreateUser, String dataCreateTime, String dataUpdateUser, String dataUpdateTime, Integer dataVersion) {
        this.flowStepId = flowStepId;
        this.flowStepGroup = flowStepGroup;
        this.flowStepName = flowStepName;
        this.isEnabled = isEnabled;
        this.dataCreateUser = dataCreateUser;
        this.dataCreateTime = dataCreateTime;
        this.dataUpdateUser = dataUpdateUser;
        this.dataUpdateTime = dataUpdateTime;
        this.dataVersion = dataVersion;
    }

    public SdpBatchStep() {
        super();
    }

    public String getFlowStepId() {
        return flowStepId;
    }

    public void setFlowStepId(String flowStepId) {
        this.flowStepId = flowStepId == null ? null : flowStepId.trim();
    }

    public String getFlowStepGroup() {
        return flowStepGroup;
    }

    public void setFlowStepGroup(String flowStepGroup) {
        this.flowStepGroup = flowStepGroup == null ? null : flowStepGroup.trim();
    }

    public String getFlowStepName() {
        return flowStepName;
    }

    public void setFlowStepName(String flowStepName) {
        this.flowStepName = flowStepName == null ? null : flowStepName.trim();
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
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
        sb.append(", flowStepId=").append(flowStepId);
        sb.append(", flowStepGroup=").append(flowStepGroup);
        sb.append(", flowStepName=").append(flowStepName);
        sb.append(", isEnabled=").append(isEnabled);
        sb.append(", dataCreateUser=").append(dataCreateUser);
        sb.append(", dataCreateTime=").append(dataCreateTime);
        sb.append(", dataUpdateUser=").append(dataUpdateUser);
        sb.append(", dataUpdateTime=").append(dataUpdateTime);
        sb.append(", dataVersion=").append(dataVersion);
        sb.append("]");
        return sb.toString();
    }
}