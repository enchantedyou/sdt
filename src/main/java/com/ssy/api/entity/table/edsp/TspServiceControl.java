package com.ssy.api.entity.table.edsp;

public class TspServiceControl {
    private String systemCode;

    private String subSystemCode;

    private String serviceInvokeId;

    private String innerServiceCode;

    private String innerServiceImplCode;

    private String description;

    private String routeKeys;

    private String cancelService;

    private String confirmService;

    private String serviceTransactionMode;

    private String serviceType;

    private Integer registCallLog;

    private String serviceExecutorType;

    public TspServiceControl(String systemCode, String subSystemCode, String serviceInvokeId, String innerServiceCode, String innerServiceImplCode, String description, String routeKeys, String cancelService, String confirmService, String serviceTransactionMode, String serviceType, Integer registCallLog, String serviceExecutorType) {
        this.systemCode = systemCode;
        this.subSystemCode = subSystemCode;
        this.serviceInvokeId = serviceInvokeId;
        this.innerServiceCode = innerServiceCode;
        this.innerServiceImplCode = innerServiceImplCode;
        this.description = description;
        this.routeKeys = routeKeys;
        this.cancelService = cancelService;
        this.confirmService = confirmService;
        this.serviceTransactionMode = serviceTransactionMode;
        this.serviceType = serviceType;
        this.registCallLog = registCallLog;
        this.serviceExecutorType = serviceExecutorType;
    }

    public TspServiceControl() {
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

    public String getRouteKeys() {
        return routeKeys;
    }

    public void setRouteKeys(String routeKeys) {
        this.routeKeys = routeKeys == null ? null : routeKeys.trim();
    }

    public String getCancelService() {
        return cancelService;
    }

    public void setCancelService(String cancelService) {
        this.cancelService = cancelService == null ? null : cancelService.trim();
    }

    public String getConfirmService() {
        return confirmService;
    }

    public void setConfirmService(String confirmService) {
        this.confirmService = confirmService == null ? null : confirmService.trim();
    }

    public String getServiceTransactionMode() {
        return serviceTransactionMode;
    }

    public void setServiceTransactionMode(String serviceTransactionMode) {
        this.serviceTransactionMode = serviceTransactionMode == null ? null : serviceTransactionMode.trim();
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType == null ? null : serviceType.trim();
    }

    public Integer getRegistCallLog() {
        return registCallLog;
    }

    public void setRegistCallLog(Integer registCallLog) {
        this.registCallLog = registCallLog;
    }

    public String getServiceExecutorType() {
        return serviceExecutorType;
    }

    public void setServiceExecutorType(String serviceExecutorType) {
        this.serviceExecutorType = serviceExecutorType == null ? null : serviceExecutorType.trim();
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
        sb.append(", innerServiceImplCode=").append(innerServiceImplCode);
        sb.append(", description=").append(description);
        sb.append(", routeKeys=").append(routeKeys);
        sb.append(", cancelService=").append(cancelService);
        sb.append(", confirmService=").append(confirmService);
        sb.append(", serviceTransactionMode=").append(serviceTransactionMode);
        sb.append(", serviceType=").append(serviceType);
        sb.append(", registCallLog=").append(registCallLog);
        sb.append(", serviceExecutorType=").append(serviceExecutorType);
        sb.append("]");
        return sb.toString();
    }
}