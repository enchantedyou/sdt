package com.ssy.api.entity.type.local;

import com.ssy.api.entity.enums.E_DATAOPERATE;

public class SdDatasourceEdit {
    private String datasourceId;

    private String datasourceType;

    private String tlsqlInd;

    private String platformTablePrefix;

    private String datasourceDesc;

    private String datasourceDriver;

    private String datasourceUrl;

    private String datasourceUser;

    private String datasourcePwd;

    private String dataCreateUser;

    private String dataCreateTime;

    private String dataUpdateUser;

    private String dataUpdateTime;

    private Integer dataVersion;

    private E_DATAOPERATE dataOperateType;

    public SdDatasourceEdit(String datasourceId, String datasourceType, String tlsqlInd, String platformTablePrefix, String datasourceDesc, String datasourceDriver, String datasourceUrl, String datasourceUser, String datasourcePwd, String dataCreateUser, String dataCreateTime, String dataUpdateUser, String dataUpdateTime, Integer dataVersion, E_DATAOPERATE dataOperateType) {
        this.datasourceId = datasourceId;
        this.datasourceType = datasourceType;
        this.tlsqlInd = tlsqlInd;
        this.platformTablePrefix = platformTablePrefix;
        this.datasourceDesc = datasourceDesc;
        this.datasourceDriver = datasourceDriver;
        this.datasourceUrl = datasourceUrl;
        this.datasourceUser = datasourceUser;
        this.datasourcePwd = datasourcePwd;
        this.dataCreateUser = dataCreateUser;
        this.dataCreateTime = dataCreateTime;
        this.dataUpdateUser = dataUpdateUser;
        this.dataUpdateTime = dataUpdateTime;
        this.dataVersion = dataVersion;
        this.dataOperateType = dataOperateType;
    }

    public SdDatasourceEdit() {
        super();
    }

    public String getDatasourceId() {
        return datasourceId;
    }

    public void setDatasourceId(String datasourceId) {
        this.datasourceId = datasourceId == null ? null : datasourceId.trim();
    }

    public String getDatasourceType() {
        return datasourceType;
    }

    public void setDatasourceType(String datasourceType) {
        this.datasourceType = datasourceType == null ? null : datasourceType.trim();
    }

    public String getTlsqlInd() {
        return tlsqlInd;
    }

    public void setTlsqlInd(String tlsqlInd) {
        this.tlsqlInd = tlsqlInd == null ? null : tlsqlInd.trim();
    }

    public String getPlatformTablePrefix() {
        return platformTablePrefix;
    }

    public void setPlatformTablePrefix(String platformTablePrefix) {
        this.platformTablePrefix = platformTablePrefix == null ? null : platformTablePrefix.trim();
    }

    public String getDatasourceDesc() {
        return datasourceDesc;
    }

    public void setDatasourceDesc(String datasourceDesc) {
        this.datasourceDesc = datasourceDesc == null ? null : datasourceDesc.trim();
    }

    public String getDatasourceDriver() {
        return datasourceDriver;
    }

    public void setDatasourceDriver(String datasourceDriver) {
        this.datasourceDriver = datasourceDriver == null ? null : datasourceDriver.trim();
    }

    public String getDatasourceUrl() {
        return datasourceUrl;
    }

    public void setDatasourceUrl(String datasourceUrl) {
        this.datasourceUrl = datasourceUrl == null ? null : datasourceUrl.trim();
    }

    public String getDatasourceUser() {
        return datasourceUser;
    }

    public void setDatasourceUser(String datasourceUser) {
        this.datasourceUser = datasourceUser == null ? null : datasourceUser.trim();
    }

    public String getDatasourcePwd() {
        return datasourcePwd;
    }

    public void setDatasourcePwd(String datasourcePwd) {
        this.datasourcePwd = datasourcePwd == null ? null : datasourcePwd.trim();
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

    public E_DATAOPERATE getDataOperateType() {
        return dataOperateType;
    }

    public void setDataOperateType(E_DATAOPERATE dataOperateType) {
        this.dataOperateType = dataOperateType;
    }

    @Override
    public String toString() {
        return "SdDatasourceEdit{" +
                "datasourceId='" + datasourceId + '\'' +
                ", datasourceType='" + datasourceType + '\'' +
                ", tlsqlInd='" + tlsqlInd + '\'' +
                ", platformTablePrefix='" + platformTablePrefix + '\'' +
                ", datasourceDesc='" + datasourceDesc + '\'' +
                ", datasourceDriver='" + datasourceDriver + '\'' +
                ", datasourceUrl='" + datasourceUrl + '\'' +
                ", datasourceUser='" + datasourceUser + '\'' +
                ", datasourcePwd='" + datasourcePwd + '\'' +
                ", dataCreateUser='" + dataCreateUser + '\'' +
                ", dataCreateTime='" + dataCreateTime + '\'' +
                ", dataUpdateUser='" + dataUpdateUser + '\'' +
                ", dataUpdateTime='" + dataUpdateTime + '\'' +
                ", dataVersion=" + dataVersion +
                ", dataOperateType=" + dataOperateType +
                '}';
    }
}