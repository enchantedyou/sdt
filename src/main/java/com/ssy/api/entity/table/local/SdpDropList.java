package com.ssy.api.entity.table.local;

public class SdpDropList {
    private String dropListType;

    private String dropListValue;

    private String dropListDesc;

    private Integer dataSort;

    private String enableInd;

    private String dataCreateUser;

    private String dataCreateTime;

    private String dataUpdateUser;

    private String dataUpdateTime;

    private Integer dataVersion;

    public SdpDropList(String dropListType, String dropListValue, String dropListDesc, Integer dataSort, String enableInd, String dataCreateUser, String dataCreateTime, String dataUpdateUser, String dataUpdateTime, Integer dataVersion) {
        this.dropListType = dropListType;
        this.dropListValue = dropListValue;
        this.dropListDesc = dropListDesc;
        this.dataSort = dataSort;
        this.enableInd = enableInd;
        this.dataCreateUser = dataCreateUser;
        this.dataCreateTime = dataCreateTime;
        this.dataUpdateUser = dataUpdateUser;
        this.dataUpdateTime = dataUpdateTime;
        this.dataVersion = dataVersion;
    }

    public SdpDropList() {
        super();
    }

    public String getDropListType() {
        return dropListType;
    }

    public void setDropListType(String dropListType) {
        this.dropListType = dropListType == null ? null : dropListType.trim();
    }

    public String getDropListValue() {
        return dropListValue;
    }

    public void setDropListValue(String dropListValue) {
        this.dropListValue = dropListValue == null ? null : dropListValue.trim();
    }

    public String getDropListDesc() {
        return dropListDesc;
    }

    public void setDropListDesc(String dropListDesc) {
        this.dropListDesc = dropListDesc == null ? null : dropListDesc.trim();
    }

    public Integer getDataSort() {
        return dataSort;
    }

    public void setDataSort(Integer dataSort) {
        this.dataSort = dataSort;
    }

    public String getEnableInd() {
        return enableInd;
    }

    public void setEnableInd(String enableInd) {
        this.enableInd = enableInd == null ? null : enableInd.trim();
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
        sb.append(", dropListType=").append(dropListType);
        sb.append(", dropListValue=").append(dropListValue);
        sb.append(", dropListDesc=").append(dropListDesc);
        sb.append(", dataSort=").append(dataSort);
        sb.append(", enableInd=").append(enableInd);
        sb.append(", dataCreateUser=").append(dataCreateUser);
        sb.append(", dataCreateTime=").append(dataCreateTime);
        sb.append(", dataUpdateUser=").append(dataUpdateUser);
        sb.append(", dataUpdateTime=").append(dataUpdateTime);
        sb.append(", dataVersion=").append(dataVersion);
        sb.append("]");
        return sb.toString();
    }
}