package com.ssy.api.entity.table.edsp;

public class TspTaskExecutionDomain {
    private String systemCode;

    private String corporateCode;

    private String tranExecutionDomainCode;

    private Integer miniConcurrencyNum;

    private Integer maxConcurrencyNum;

    private Integer maxStepConcurrencyNum;

    private Integer miniStepConcurrencyNum;

    private Integer miniJobConcurrencyNum;

    private Integer maxJobConcurrencyNum;

    private String jobServerCluster;

    private String exeTranFlowList;

    private String exeTranGroupList;

    private String exeDomainState;

    private String subSystemCode;

    private String descMessage;

    public TspTaskExecutionDomain(String systemCode, String corporateCode, String tranExecutionDomainCode, Integer miniConcurrencyNum, Integer maxConcurrencyNum, Integer maxStepConcurrencyNum, Integer miniStepConcurrencyNum, Integer miniJobConcurrencyNum, Integer maxJobConcurrencyNum, String jobServerCluster, String exeTranFlowList, String exeTranGroupList, String exeDomainState, String subSystemCode) {
        this.systemCode = systemCode;
        this.corporateCode = corporateCode;
        this.tranExecutionDomainCode = tranExecutionDomainCode;
        this.miniConcurrencyNum = miniConcurrencyNum;
        this.maxConcurrencyNum = maxConcurrencyNum;
        this.maxStepConcurrencyNum = maxStepConcurrencyNum;
        this.miniStepConcurrencyNum = miniStepConcurrencyNum;
        this.miniJobConcurrencyNum = miniJobConcurrencyNum;
        this.maxJobConcurrencyNum = maxJobConcurrencyNum;
        this.jobServerCluster = jobServerCluster;
        this.exeTranFlowList = exeTranFlowList;
        this.exeTranGroupList = exeTranGroupList;
        this.exeDomainState = exeDomainState;
        this.subSystemCode = subSystemCode;
    }

    public TspTaskExecutionDomain(String systemCode, String corporateCode, String tranExecutionDomainCode, Integer miniConcurrencyNum, Integer maxConcurrencyNum, Integer maxStepConcurrencyNum, Integer miniStepConcurrencyNum, Integer miniJobConcurrencyNum, Integer maxJobConcurrencyNum, String jobServerCluster, String exeTranFlowList, String exeTranGroupList, String exeDomainState, String subSystemCode, String descMessage) {
        this.systemCode = systemCode;
        this.corporateCode = corporateCode;
        this.tranExecutionDomainCode = tranExecutionDomainCode;
        this.miniConcurrencyNum = miniConcurrencyNum;
        this.maxConcurrencyNum = maxConcurrencyNum;
        this.maxStepConcurrencyNum = maxStepConcurrencyNum;
        this.miniStepConcurrencyNum = miniStepConcurrencyNum;
        this.miniJobConcurrencyNum = miniJobConcurrencyNum;
        this.maxJobConcurrencyNum = maxJobConcurrencyNum;
        this.jobServerCluster = jobServerCluster;
        this.exeTranFlowList = exeTranFlowList;
        this.exeTranGroupList = exeTranGroupList;
        this.exeDomainState = exeDomainState;
        this.subSystemCode = subSystemCode;
        this.descMessage = descMessage;
    }

    public TspTaskExecutionDomain() {
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

    public String getTranExecutionDomainCode() {
        return tranExecutionDomainCode;
    }

    public void setTranExecutionDomainCode(String tranExecutionDomainCode) {
        this.tranExecutionDomainCode = tranExecutionDomainCode == null ? null : tranExecutionDomainCode.trim();
    }

    public Integer getMiniConcurrencyNum() {
        return miniConcurrencyNum;
    }

    public void setMiniConcurrencyNum(Integer miniConcurrencyNum) {
        this.miniConcurrencyNum = miniConcurrencyNum;
    }

    public Integer getMaxConcurrencyNum() {
        return maxConcurrencyNum;
    }

    public void setMaxConcurrencyNum(Integer maxConcurrencyNum) {
        this.maxConcurrencyNum = maxConcurrencyNum;
    }

    public Integer getMaxStepConcurrencyNum() {
        return maxStepConcurrencyNum;
    }

    public void setMaxStepConcurrencyNum(Integer maxStepConcurrencyNum) {
        this.maxStepConcurrencyNum = maxStepConcurrencyNum;
    }

    public Integer getMiniStepConcurrencyNum() {
        return miniStepConcurrencyNum;
    }

    public void setMiniStepConcurrencyNum(Integer miniStepConcurrencyNum) {
        this.miniStepConcurrencyNum = miniStepConcurrencyNum;
    }

    public Integer getMiniJobConcurrencyNum() {
        return miniJobConcurrencyNum;
    }

    public void setMiniJobConcurrencyNum(Integer miniJobConcurrencyNum) {
        this.miniJobConcurrencyNum = miniJobConcurrencyNum;
    }

    public Integer getMaxJobConcurrencyNum() {
        return maxJobConcurrencyNum;
    }

    public void setMaxJobConcurrencyNum(Integer maxJobConcurrencyNum) {
        this.maxJobConcurrencyNum = maxJobConcurrencyNum;
    }

    public String getJobServerCluster() {
        return jobServerCluster;
    }

    public void setJobServerCluster(String jobServerCluster) {
        this.jobServerCluster = jobServerCluster == null ? null : jobServerCluster.trim();
    }

    public String getExeTranFlowList() {
        return exeTranFlowList;
    }

    public void setExeTranFlowList(String exeTranFlowList) {
        this.exeTranFlowList = exeTranFlowList == null ? null : exeTranFlowList.trim();
    }

    public String getExeTranGroupList() {
        return exeTranGroupList;
    }

    public void setExeTranGroupList(String exeTranGroupList) {
        this.exeTranGroupList = exeTranGroupList == null ? null : exeTranGroupList.trim();
    }

    public String getExeDomainState() {
        return exeDomainState;
    }

    public void setExeDomainState(String exeDomainState) {
        this.exeDomainState = exeDomainState == null ? null : exeDomainState.trim();
    }

    public String getSubSystemCode() {
        return subSystemCode;
    }

    public void setSubSystemCode(String subSystemCode) {
        this.subSystemCode = subSystemCode == null ? null : subSystemCode.trim();
    }

    public String getDescMessage() {
        return descMessage;
    }

    public void setDescMessage(String descMessage) {
        this.descMessage = descMessage == null ? null : descMessage.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", systemCode=").append(systemCode);
        sb.append(", corporateCode=").append(corporateCode);
        sb.append(", tranExecutionDomainCode=").append(tranExecutionDomainCode);
        sb.append(", miniConcurrencyNum=").append(miniConcurrencyNum);
        sb.append(", maxConcurrencyNum=").append(maxConcurrencyNum);
        sb.append(", maxStepConcurrencyNum=").append(maxStepConcurrencyNum);
        sb.append(", miniStepConcurrencyNum=").append(miniStepConcurrencyNum);
        sb.append(", miniJobConcurrencyNum=").append(miniJobConcurrencyNum);
        sb.append(", maxJobConcurrencyNum=").append(maxJobConcurrencyNum);
        sb.append(", jobServerCluster=").append(jobServerCluster);
        sb.append(", exeTranFlowList=").append(exeTranFlowList);
        sb.append(", exeTranGroupList=").append(exeTranGroupList);
        sb.append(", exeDomainState=").append(exeDomainState);
        sb.append(", subSystemCode=").append(subSystemCode);
        sb.append(", descMessage=").append(descMessage);
        sb.append("]");
        return sb.toString();
    }
}