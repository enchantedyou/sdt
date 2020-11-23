package com.ssy.api.entity.table.ct;

public class CtpLanguagePacket {
    private String languageResourceType;

    private String languageResourceKey;

    private String uiLanguage;

    private String languageResourceValue;

    private String dataCreateTime;

    private String dataUpdateTime;

    private String dataCreateUser;

    private String dataUpdateUser;

    private Long dataVersion;

    public CtpLanguagePacket(String languageResourceType, String languageResourceKey, String uiLanguage, String languageResourceValue, String dataCreateTime, String dataUpdateTime, String dataCreateUser, String dataUpdateUser, Long dataVersion) {
        this.languageResourceType = languageResourceType;
        this.languageResourceKey = languageResourceKey;
        this.uiLanguage = uiLanguage;
        this.languageResourceValue = languageResourceValue;
        this.dataCreateTime = dataCreateTime;
        this.dataUpdateTime = dataUpdateTime;
        this.dataCreateUser = dataCreateUser;
        this.dataUpdateUser = dataUpdateUser;
        this.dataVersion = dataVersion;
    }

    public CtpLanguagePacket() {
        super();
    }

    public String getLanguageResourceType() {
        return languageResourceType;
    }

    public void setLanguageResourceType(String languageResourceType) {
        this.languageResourceType = languageResourceType == null ? null : languageResourceType.trim();
    }

    public String getLanguageResourceKey() {
        return languageResourceKey;
    }

    public void setLanguageResourceKey(String languageResourceKey) {
        this.languageResourceKey = languageResourceKey == null ? null : languageResourceKey.trim();
    }

    public String getUiLanguage() {
        return uiLanguage;
    }

    public void setUiLanguage(String uiLanguage) {
        this.uiLanguage = uiLanguage == null ? null : uiLanguage.trim();
    }

    public String getLanguageResourceValue() {
        return languageResourceValue;
    }

    public void setLanguageResourceValue(String languageResourceValue) {
        this.languageResourceValue = languageResourceValue == null ? null : languageResourceValue.trim();
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
        sb.append(", languageResourceType=").append(languageResourceType);
        sb.append(", languageResourceKey=").append(languageResourceKey);
        sb.append(", uiLanguage=").append(uiLanguage);
        sb.append(", languageResourceValue=").append(languageResourceValue);
        sb.append(", dataCreateTime=").append(dataCreateTime);
        sb.append(", dataUpdateTime=").append(dataUpdateTime);
        sb.append(", dataCreateUser=").append(dataCreateUser);
        sb.append(", dataUpdateUser=").append(dataUpdateUser);
        sb.append(", dataVersion=").append(dataVersion);
        sb.append("]");
        return sb.toString();
    }
}