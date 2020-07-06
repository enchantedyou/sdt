package com.ssy.api.entity.table.local;

public class SdpBatchFlow {
    private String systemCode;

    private String subSystemCode;

    private String flowGroup;

    private String datasourceId;

    private String flowDesc;

    private Integer flowOrder;

    private Boolean isEnabled;

    private String dataCreateUser;

    private String dataCreateTime;

    private String dataUpdateUser;

    private String dataUpdateTime;

    private Integer dataVersion;

    public SdpBatchFlow(String systemCode, String subSystemCode, String flowGroup, String datasourceId, String flowDesc, Integer flowOrder, Boolean isEnabled, String dataCreateUser, String dataCreateTime, String dataUpdateUser, String dataUpdateTime, Integer dataVersion) {
        this.systemCode = systemCode;
        this.subSystemCode = subSystemCode;
        this.flowGroup = flowGroup;
        this.datasourceId = datasourceId;
        this.flowDesc = flowDesc;
        this.flowOrder = flowOrder;
        this.isEnabled = isEnabled;
        this.dataCreateUser = dataCreateUser;
        this.dataCreateTime = dataCreateTime;
        this.dataUpdateUser = dataUpdateUser;
        this.dataUpdateTime = dataUpdateTime;
        this.dataVersion = dataVersion;
    }

    public SdpBatchFlow() {
        super();
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode == null ? null : systemCode.trim();
    }

    public String getSubSystemCode() {
        return subSystemCode;
    }

    public void setSubSystemCode(String subSystemCode) {
        this.subSystemCode = subSystemCode == null ? null : subSystemCode.trim();
    }

    public String getFlowGroup() {
        return flowGroup;
    }

    public void setFlowGroup(String flowGroup) {
        this.flowGroup = flowGroup == null ? null : flowGroup.trim();
    }

    public String getDatasourceId() {
        return datasourceId;
    }

    public void setDatasourceId(String datasourceId) {
        this.datasourceId = datasourceId == null ? null : datasourceId.trim();
    }

    public String getFlowDesc() {
        return flowDesc;
    }

    public void setFlowDesc(String flowDesc) {
        this.flowDesc = flowDesc == null ? null : flowDesc.trim();
    }

    public Integer getFlowOrder() {
        return flowOrder;
    }

    public void setFlowOrder(Integer flowOrder) {
        this.flowOrder = flowOrder;
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
        sb.append(", systemCode=").append(systemCode);
        sb.append(", subSystemCode=").append(subSystemCode);
        sb.append(", flowGroup=").append(flowGroup);
        sb.append(", datasourceId=").append(datasourceId);
        sb.append(", flowDesc=").append(flowDesc);
        sb.append(", flowOrder=").append(flowOrder);
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