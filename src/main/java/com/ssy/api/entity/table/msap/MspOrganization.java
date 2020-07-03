package com.ssy.api.entity.table.msap;

public class MspOrganization {
    private String busiOrgId;

    private String orgName;

    private String orgAddress;

    private String defaultOrgInd;

    private String refOrgId;

    private String dataCreateTime;

    private String dataUpdateTime;

    private String dataCreateUser;

    private String dataUpdateUser;

    private Long dataVersion;

    public MspOrganization(String busiOrgId, String orgName, String orgAddress, String defaultOrgInd, String refOrgId, String dataCreateTime, String dataUpdateTime, String dataCreateUser, String dataUpdateUser, Long dataVersion) {
        this.busiOrgId = busiOrgId;
        this.orgName = orgName;
        this.orgAddress = orgAddress;
        this.defaultOrgInd = defaultOrgInd;
        this.refOrgId = refOrgId;
        this.dataCreateTime = dataCreateTime;
        this.dataUpdateTime = dataUpdateTime;
        this.dataCreateUser = dataCreateUser;
        this.dataUpdateUser = dataUpdateUser;
        this.dataVersion = dataVersion;
    }

    public MspOrganization() {
        super();
    }

    public String getBusiOrgId() {
        return busiOrgId;
    }

    public void setBusiOrgId(String busiOrgId) {
        this.busiOrgId = busiOrgId == null ? null : busiOrgId.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress == null ? null : orgAddress.trim();
    }

    public String getDefaultOrgInd() {
        return defaultOrgInd;
    }

    public void setDefaultOrgInd(String defaultOrgInd) {
        this.defaultOrgInd = defaultOrgInd == null ? null : defaultOrgInd.trim();
    }

    public String getRefOrgId() {
        return refOrgId;
    }

    public void setRefOrgId(String refOrgId) {
        this.refOrgId = refOrgId == null ? null : refOrgId.trim();
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
        sb.append(", busiOrgId=").append(busiOrgId);
        sb.append(", orgName=").append(orgName);
        sb.append(", orgAddress=").append(orgAddress);
        sb.append(", defaultOrgInd=").append(defaultOrgInd);
        sb.append(", refOrgId=").append(refOrgId);
        sb.append(", dataCreateTime=").append(dataCreateTime);
        sb.append(", dataUpdateTime=").append(dataUpdateTime);
        sb.append(", dataCreateUser=").append(dataCreateUser);
        sb.append(", dataUpdateUser=").append(dataUpdateUser);
        sb.append(", dataVersion=").append(dataVersion);
        sb.append("]");
        return sb.toString();
    }
}