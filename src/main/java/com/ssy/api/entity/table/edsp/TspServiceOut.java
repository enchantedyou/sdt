package com.ssy.api.entity.table.edsp;

public class TspServiceOut {
    private String systemCode;

    private String subSystemCode;

    private String serviceInvokeId;

    private String innerServiceCode;

    private String outServiceApp;

    private String outServiceCode;

    private String outServiceGroup;

    private String outServiceVersion;

    private String description;

    private String protocolId;

    private Integer timeout;

    private Integer timeoutRedoCounts;

    private Integer timeoutConfirm;

    private Integer aliasMapping;

    private Integer registCallLog;

    public TspServiceOut(String systemCode, String subSystemCode, String serviceInvokeId, String innerServiceCode, String outServiceApp, String outServiceCode, String outServiceGroup, String outServiceVersion, String description, String protocolId, Integer timeout, Integer timeoutRedoCounts, Integer timeoutConfirm, Integer aliasMapping, Integer registCallLog) {
        this.systemCode = systemCode;
        this.subSystemCode = subSystemCode;
        this.serviceInvokeId = serviceInvokeId;
        this.innerServiceCode = innerServiceCode;
        this.outServiceApp = outServiceApp;
        this.outServiceCode = outServiceCode;
        this.outServiceGroup = outServiceGroup;
        this.outServiceVersion = outServiceVersion;
        this.description = description;
        this.protocolId = protocolId;
        this.timeout = timeout;
        this.timeoutRedoCounts = timeoutRedoCounts;
        this.timeoutConfirm = timeoutConfirm;
        this.aliasMapping = aliasMapping;
        this.registCallLog = registCallLog;
    }

    public TspServiceOut() {
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

    public String getServiceInvokeId() {
        return serviceInvokeId;
    }

    public void setServiceInvokeId(String serviceInvokeId) {
        this.serviceInvokeId = serviceInvokeId == null ? null : serviceInvokeId.trim();
    }

    public String getInnerServiceCode() {
        return innerServiceCode;
    }

    public void setInnerServiceCode(String innerServiceCode) {
        this.innerServiceCode = innerServiceCode == null ? null : innerServiceCode.trim();
    }

    public String getOutServiceApp() {
        return outServiceApp;
    }

    public void setOutServiceApp(String outServiceApp) {
        this.outServiceApp = outServiceApp == null ? null : outServiceApp.trim();
    }

    public String getOutServiceCode() {
        return outServiceCode;
    }

    public void setOutServiceCode(String outServiceCode) {
        this.outServiceCode = outServiceCode == null ? null : outServiceCode.trim();
    }

    public String getOutServiceGroup() {
        return outServiceGroup;
    }

    public void setOutServiceGroup(String outServiceGroup) {
        this.outServiceGroup = outServiceGroup == null ? null : outServiceGroup.trim();
    }

    public String getOutServiceVersion() {
        return outServiceVersion;
    }

    public void setOutServiceVersion(String outServiceVersion) {
        this.outServiceVersion = outServiceVersion == null ? null : outServiceVersion.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(String protocolId) {
        this.protocolId = protocolId == null ? null : protocolId.trim();
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getTimeoutRedoCounts() {
        return timeoutRedoCounts;
    }

    public void setTimeoutRedoCounts(Integer timeoutRedoCounts) {
        this.timeoutRedoCounts = timeoutRedoCounts;
    }

    public Integer getTimeoutConfirm() {
        return timeoutConfirm;
    }

    public void setTimeoutConfirm(Integer timeoutConfirm) {
        this.timeoutConfirm = timeoutConfirm;
    }

    public Integer getAliasMapping() {
        return aliasMapping;
    }

    public void setAliasMapping(Integer aliasMapping) {
        this.aliasMapping = aliasMapping;
    }

    public Integer getRegistCallLog() {
        return registCallLog;
    }

    public void setRegistCallLog(Integer registCallLog) {
        this.registCallLog = registCallLog;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", systemCode=").append(systemCode);
        sb.append(", subSystemCode=").append(subSystemCode);
        sb.append(", serviceInvokeId=").append(serviceInvokeId);
        sb.append(", innerServiceCode=").append(innerServiceCode);
        sb.append(", outServiceApp=").append(outServiceApp);
        sb.append(", outServiceCode=").append(outServiceCode);
        sb.append(", outServiceGroup=").append(outServiceGroup);
        sb.append(", outServiceVersion=").append(outServiceVersion);
        sb.append(", description=").append(description);
        sb.append(", protocolId=").append(protocolId);
        sb.append(", timeout=").append(timeout);
        sb.append(", timeoutRedoCounts=").append(timeoutRedoCounts);
        sb.append(", timeoutConfirm=").append(timeoutConfirm);
        sb.append(", aliasMapping=").append(aliasMapping);
        sb.append(", registCallLog=").append(registCallLog);
        sb.append("]");
        return sb.toString();
    }
}