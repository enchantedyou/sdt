package com.ssy.api.entity.table.edsp;

public class TspServiceIn {
    private String systemCode;

    private String subSystemCode;

    private String outServiceCode;

    private String innerServiceCode;

    private String innerServiceImplCode;

    private String description;

    private String serviceCategory;

    private String routeKeys;

    private String serviceType;

    private String protocolId;

    private Integer isEnable;

    private String transactionMode;

    private String logLevel;

    private Integer timeout;

    private Integer aliasMapping;

    private Integer forceUnusedOdbCache;

    private String registerMode;

    public TspServiceIn(String systemCode, String subSystemCode, String outServiceCode, String innerServiceCode, String innerServiceImplCode, String description, String serviceCategory, String routeKeys, String serviceType, String protocolId, Integer isEnable, String transactionMode, String logLevel, Integer timeout, Integer aliasMapping, Integer forceUnusedOdbCache, String registerMode) {
        this.systemCode = systemCode;
        this.subSystemCode = subSystemCode;
        this.outServiceCode = outServiceCode;
        this.innerServiceCode = innerServiceCode;
        this.innerServiceImplCode = innerServiceImplCode;
        this.description = description;
        this.serviceCategory = serviceCategory;
        this.routeKeys = routeKeys;
        this.serviceType = serviceType;
        this.protocolId = protocolId;
        this.isEnable = isEnable;
        this.transactionMode = transactionMode;
        this.logLevel = logLevel;
        this.timeout = timeout;
        this.aliasMapping = aliasMapping;
        this.forceUnusedOdbCache = forceUnusedOdbCache;
        this.registerMode = registerMode;
    }

    public TspServiceIn() {
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

    public String getOutServiceCode() {
        return outServiceCode;
    }

    public void setOutServiceCode(String outServiceCode) {
        this.outServiceCode = outServiceCode == null ? null : outServiceCode.trim();
    }

    public String getInnerServiceCode() {
        return innerServiceCode;
    }

    public void setInnerServiceCode(String innerServiceCode) {
        this.innerServiceCode = innerServiceCode == null ? null : innerServiceCode.trim();
    }

    public String getInnerServiceImplCode() {
        return innerServiceImplCode;
    }

    public void setInnerServiceImplCode(String innerServiceImplCode) {
        this.innerServiceImplCode = innerServiceImplCode == null ? null : innerServiceImplCode.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(String serviceCategory) {
        this.serviceCategory = serviceCategory == null ? null : serviceCategory.trim();
    }

    public String getRouteKeys() {
        return routeKeys;
    }

    public void setRouteKeys(String routeKeys) {
        this.routeKeys = routeKeys == null ? null : routeKeys.trim();
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType == null ? null : serviceType.trim();
    }

    public String getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(String protocolId) {
        this.protocolId = protocolId == null ? null : protocolId.trim();
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public String getTransactionMode() {
        return transactionMode;
    }

    public void setTransactionMode(String transactionMode) {
        this.transactionMode = transactionMode == null ? null : transactionMode.trim();
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel == null ? null : logLevel.trim();
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getAliasMapping() {
        return aliasMapping;
    }

    public void setAliasMapping(Integer aliasMapping) {
        this.aliasMapping = aliasMapping;
    }

    public Integer getForceUnusedOdbCache() {
        return forceUnusedOdbCache;
    }

    public void setForceUnusedOdbCache(Integer forceUnusedOdbCache) {
        this.forceUnusedOdbCache = forceUnusedOdbCache;
    }

    public String getRegisterMode() {
        return registerMode;
    }

    public void setRegisterMode(String registerMode) {
        this.registerMode = registerMode == null ? null : registerMode.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", systemCode=").append(systemCode);
        sb.append(", subSystemCode=").append(subSystemCode);
        sb.append(", outServiceCode=").append(outServiceCode);
        sb.append(", innerServiceCode=").append(innerServiceCode);
        sb.append(", innerServiceImplCode=").append(innerServiceImplCode);
        sb.append(", description=").append(description);
        sb.append(", serviceCategory=").append(serviceCategory);
        sb.append(", routeKeys=").append(routeKeys);
        sb.append(", serviceType=").append(serviceType);
        sb.append(", protocolId=").append(protocolId);
        sb.append(", isEnable=").append(isEnable);
        sb.append(", transactionMode=").append(transactionMode);
        sb.append(", logLevel=").append(logLevel);
        sb.append(", timeout=").append(timeout);
        sb.append(", aliasMapping=").append(aliasMapping);
        sb.append(", forceUnusedOdbCache=").append(forceUnusedOdbCache);
        sb.append(", registerMode=").append(registerMode);
        sb.append("]");
        return sb.toString();
    }
}