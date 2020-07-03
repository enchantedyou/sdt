package com.ssy.api.entity.table.ap;

public class AppDate {
    private String busiOrgId;

    private String lastDate;

    private String trxnDate;

    private String nextDate;

    private String balSheetDate;

    private String dataCreateTime;

    private String dataUpdateTime;

    private String dataCreateUser;

    private String dataUpdateUser;

    private Long dataVersion;

    public AppDate(String busiOrgId, String lastDate, String trxnDate, String nextDate, String balSheetDate, String dataCreateTime, String dataUpdateTime, String dataCreateUser, String dataUpdateUser, Long dataVersion) {
        this.busiOrgId = busiOrgId;
        this.lastDate = lastDate;
        this.trxnDate = trxnDate;
        this.nextDate = nextDate;
        this.balSheetDate = balSheetDate;
        this.dataCreateTime = dataCreateTime;
        this.dataUpdateTime = dataUpdateTime;
        this.dataCreateUser = dataCreateUser;
        this.dataUpdateUser = dataUpdateUser;
        this.dataVersion = dataVersion;
    }

    public AppDate() {
        super();
    }

    public String getBusiOrgId() {
        return busiOrgId;
    }

    public void setBusiOrgId(String busiOrgId) {
        this.busiOrgId = busiOrgId == null ? null : busiOrgId.trim();
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate == null ? null : lastDate.trim();
    }

    public String getTrxnDate() {
        return trxnDate;
    }

    public void setTrxnDate(String trxnDate) {
        this.trxnDate = trxnDate == null ? null : trxnDate.trim();
    }

    public String getNextDate() {
        return nextDate;
    }

    public void setNextDate(String nextDate) {
        this.nextDate = nextDate == null ? null : nextDate.trim();
    }

    public String getBalSheetDate() {
        return balSheetDate;
    }

    public void setBalSheetDate(String balSheetDate) {
        this.balSheetDate = balSheetDate == null ? null : balSheetDate.trim();
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
        sb.append(", lastDate=").append(lastDate);
        sb.append(", trxnDate=").append(trxnDate);
        sb.append(", nextDate=").append(nextDate);
        sb.append(", balSheetDate=").append(balSheetDate);
        sb.append(", dataCreateTime=").append(dataCreateTime);
        sb.append(", dataUpdateTime=").append(dataUpdateTime);
        sb.append(", dataCreateUser=").append(dataCreateUser);
        sb.append(", dataUpdateUser=").append(dataUpdateUser);
        sb.append(", dataVersion=").append(dataVersion);
        sb.append("]");
        return sb.toString();
    }
}