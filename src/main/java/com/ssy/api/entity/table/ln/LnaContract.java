package com.ssy.api.entity.table.ln;

import java.math.BigDecimal;

public class LnaContract {
    private String contractNo;

    private String orgId;

    private BigDecimal contractAmt;

    private String contractStatus;

    private String dataCreateTime;

    private String dataUpdateTime;

    private String dataCreateUser;

    private String dataUpdateUser;

    private Long dataVersion;

    public LnaContract(String contractNo, String orgId, BigDecimal contractAmt, String contractStatus, String dataCreateTime, String dataUpdateTime, String dataCreateUser, String dataUpdateUser, Long dataVersion) {
        this.contractNo = contractNo;
        this.orgId = orgId;
        this.contractAmt = contractAmt;
        this.contractStatus = contractStatus;
        this.dataCreateTime = dataCreateTime;
        this.dataUpdateTime = dataUpdateTime;
        this.dataCreateUser = dataCreateUser;
        this.dataUpdateUser = dataUpdateUser;
        this.dataVersion = dataVersion;
    }

    public LnaContract() {
        super();
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public BigDecimal getContractAmt() {
        return contractAmt;
    }

    public void setContractAmt(BigDecimal contractAmt) {
        this.contractAmt = contractAmt;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus == null ? null : contractStatus.trim();
    }

    public String getDataCreateTime() {
        return dataCreateTime;
    }

    public void setDataCreateTime(String dataCreateTime) {
        this.dataCreateTime = dataCreateTime == null ? null : dataCreateTime.trim();
    }

    public String getDataUpdateTime() {
        return dataUpdateTime;
    }

    public void setDataUpdateTime(String dataUpdateTime) {
        this.dataUpdateTime = dataUpdateTime == null ? null : dataUpdateTime.trim();
    }

    public String getDataCreateUser() {
        return dataCreateUser;
    }

    public void setDataCreateUser(String dataCreateUser) {
        this.dataCreateUser = dataCreateUser == null ? null : dataCreateUser.trim();
    }

    public String getDataUpdateUser() {
        return dataUpdateUser;
    }

    public void setDataUpdateUser(String dataUpdateUser) {
        this.dataUpdateUser = dataUpdateUser == null ? null : dataUpdateUser.trim();
    }

    public Long getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(Long dataVersion) {
        this.dataVersion = dataVersion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", contractNo=").append(contractNo);
        sb.append(", orgId=").append(orgId);
        sb.append(", contractAmt=").append(contractAmt);
        sb.append(", contractStatus=").append(contractStatus);
        sb.append(", dataCreateTime=").append(dataCreateTime);
        sb.append(", dataUpdateTime=").append(dataUpdateTime);
        sb.append(", dataCreateUser=").append(dataCreateUser);
        sb.append(", dataUpdateUser=").append(dataUpdateUser);
        sb.append(", dataVersion=").append(dataVersion);
        sb.append("]");
        return sb.toString();
    }
}