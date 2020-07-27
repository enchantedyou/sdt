package com.ssy.api.entity.type.local;

/**
 * @Description
 * @Author sunshaoyu
 * @Date 2020年07月11日-14:12
 */
public class SdLoginIn {

    private String userAcct;
    private String userPwd;
    private String datasourceId;

    public String getUserAcct() {
        return userAcct;
    }

    public void setUserAcct(String userAcct) {
        this.userAcct = userAcct;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getDatasourceId() {
        return datasourceId;
    }

    public void setDatasourceId(String datasourceId) {
        this.datasourceId = datasourceId;
    }

    @Override
    public String toString() {
        return "SdLoginIn{" +
                "userAcct='" + userAcct + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", datasourceId='" + datasourceId + '\'' +
                '}';
    }
}
