package com.ssy.api.entity.table.edsp;

public class TspServiceAsyncControl {
    private String systemCode;

    private String corpoCode;

    private String innerServiceCode;

    private Integer priorityLevel;

    private Integer maxThread;

    private Integer retryCount;

    private Integer redoCount;

    private String postpositionTask;

    private Long retriggerTime;

    private String dependTask;

    private Integer processMode;

    private Integer queueLength;

    private String serviceExecutorType;

    public TspServiceAsyncControl(String systemCode, String corpoCode, String innerServiceCode, Integer priorityLevel, Integer maxThread, Integer retryCount, Integer redoCount, String postpositionTask, Long retriggerTime, String dependTask, Integer processMode, Integer queueLength, String serviceExecutorType) {
        this.systemCode = systemCode;
        this.corpoCode = corpoCode;
        this.innerServiceCode = innerServiceCode;
        this.priorityLevel = priorityLevel;
        this.maxThread = maxThread;
        this.retryCount = retryCount;
        this.redoCount = redoCount;
        this.postpositionTask = postpositionTask;
        this.retriggerTime = retriggerTime;
        this.dependTask = dependTask;
        this.processMode = processMode;
        this.queueLength = queueLength;
        this.serviceExecutorType = serviceExecutorType;
    }

    public TspServiceAsyncControl() {
        super();
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode == null ? null : systemCode.trim();
    }

    public String getCorpoCode() {
        return corpoCode;
    }

    public void setCorpoCode(String corpoCode) {
        this.corpoCode = corpoCode == null ? null : corpoCode.trim();
    }

    public String getInnerServiceCode() {
        return innerServiceCode;
    }

    public void setInnerServiceCode(String innerServiceCode) {
        this.innerServiceCode = innerServiceCode == null ? null : innerServiceCode.trim();
    }

    public Integer getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(Integer priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public Integer getMaxThread() {
        return maxThread;
    }

    public void setMaxThread(Integer maxThread) {
        this.maxThread = maxThread;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public Integer getRedoCount() {
        return redoCount;
    }

    public void setRedoCount(Integer redoCount) {
        this.redoCount = redoCount;
    }

    public String getPostpositionTask() {
        return postpositionTask;
    }

    public void setPostpositionTask(String postpositionTask) {
        this.postpositionTask = postpositionTask == null ? null : postpositionTask.trim();
    }

    public Long getRetriggerTime() {
        return retriggerTime;
    }

    public void setRetriggerTime(Long retriggerTime) {
        this.retriggerTime = retriggerTime;
    }

    public String getDependTask() {
        return dependTask;
    }

    public void setDependTask(String dependTask) {
        this.dependTask = dependTask == null ? null : dependTask.trim();
    }

    public Integer getProcessMode() {
        return processMode;
    }

    public void setProcessMode(Integer processMode) {
        this.processMode = processMode;
    }

    public Integer getQueueLength() {
        return queueLength;
    }

    public void setQueueLength(Integer queueLength) {
        this.queueLength = queueLength;
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
        sb.append(", corpoCode=").append(corpoCode);
        sb.append(", innerServiceCode=").append(innerServiceCode);
        sb.append(", priorityLevel=").append(priorityLevel);
        sb.append(", maxThread=").append(maxThread);
        sb.append(", retryCount=").append(retryCount);
        sb.append(", redoCount=").append(redoCount);
        sb.append(", postpositionTask=").append(postpositionTask);
        sb.append(", retriggerTime=").append(retriggerTime);
        sb.append(", dependTask=").append(dependTask);
        sb.append(", processMode=").append(processMode);
        sb.append(", queueLength=").append(queueLength);
        sb.append(", serviceExecutorType=").append(serviceExecutorType);
        sb.append("]");
        return sb.toString();
    }
}