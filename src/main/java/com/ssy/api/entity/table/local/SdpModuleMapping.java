package com.ssy.api.entity.table.local;

public class SdpModuleMapping {
    private String moduleId;

    private String moduleDesc;

    private String serviceCodePrefix;

    private String systemCode;

    private String subSystemCode;

    private String gitlabSearchUrl;

    private String dataCreateUser;

    private String dataCreateTime;

    private String dataUpdateUser;

    private String dataUpdateTime;

    private Integer dataVersion;

    public SdpModuleMapping(String moduleId, String moduleDesc, String serviceCodePrefix, String systemCode, String subSystemCode, String gitlabSearchUrl, String dataCreateUser, String dataCreateTime, String dataUpdateUser, String dataUpdateTime, Integer dataVersion) {
        this.moduleId = moduleId;
        this.moduleDesc = moduleDesc;
        this.serviceCodePrefix = serviceCodePrefix;
        this.systemCode = systemCode;
        this.subSystemCode = subSystemCode;
        this.gitlabSearchUrl = gitlabSearchUrl;
        this.dataCreateUser = dataCreateUser;
        this.dataCreateTime = dataCreateTime;
        this.dataUpdateUser = dataUpdateUser;
        this.dataUpdateTime = dataUpdateTime;
        this.dataVersion = dataVersion;
    }

    public SdpModuleMapping() {
        super();
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId == null ? null : moduleId.trim();
    }

    public String getModuleDesc() {
        return moduleDesc;
    }

    public void setModuleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc == null ? null : moduleDesc.trim();
    }

    public String getServiceCodePrefix() {
        return serviceCodePrefix;
    }

    public void setServiceCodePrefix(String serviceCodePrefix) {
        this.serviceCodePrefix = serviceCodePrefix == null ? null : serviceCodePrefix.trim();
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

    public String getGitlabSearchUrl() {
        return gitlabSearchUrl;
    }

    public void setGitlabSearchUrl(String gitlabSearchUrl) {
        this.gitlabSearchUrl = gitlabSearchUrl == null ? null : gitlabSearchUrl.trim();
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
        sb.append(", moduleId=").append(moduleId);
        sb.append(", moduleDesc=").append(moduleDesc);
        sb.append(", serviceCodePrefix=").append(serviceCodePrefix);
        sb.append(", systemCode=").append(systemCode);
        sb.append(", subSystemCode=").append(subSystemCode);
        sb.append(", gitlabSearchUrl=").append(gitlabSearchUrl);
        sb.append(", dataCreateUser=").append(dataCreateUser);
        sb.append(", dataCreateTime=").append(dataCreateTime);
        sb.append(", dataUpdateUser=").append(dataUpdateUser);
        sb.append(", dataUpdateTime=").append(dataUpdateTime);
        sb.append(", dataVersion=").append(dataVersion);
        sb.append("]");
        return sb.toString();
    }
}