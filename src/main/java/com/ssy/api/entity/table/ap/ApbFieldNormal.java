package com.ssy.api.entity.table.ap;

/**
 * @Description 公共字段
 * @Author sunshaoyu
 * @Date 2020年06月12日-14:31
 */
public class ApbFieldNormal {

    private String dataCreateUser;
    private String dataCreateTime;
    private String dataUpdateUser;
    private String dataUpdateTime;
    private Integer dataVersion;

    public String getDataCreateUser() {
        return dataCreateUser;
    }

    public String getDataCreateTime() {
        return dataCreateTime;
    }

    public String getDataUpdateUser() {
        return dataUpdateUser;
    }

    public String getDataUpdateTime() {
        return dataUpdateTime;
    }

    public Integer getDataVersion() {
        return dataVersion;
    }

    public void setDataCreateUser(String dataCreateUser) {
        this.dataCreateUser = dataCreateUser;
    }

    public void setDataCreateTime(String dataCreateTime) {
        this.dataCreateTime = dataCreateTime;
    }

    public void setDataUpdateUser(String dataUpdateUser) {
        this.dataUpdateUser = dataUpdateUser;
    }

    public void setDataUpdateTime(String dataUpdateTime) {
        this.dataUpdateTime = dataUpdateTime;
    }

    public void setDataVersion(Integer dataVersion) {
        this.dataVersion = dataVersion;
    }
}
