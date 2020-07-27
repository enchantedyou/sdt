package com.ssy.api.entity.session;

/**
 * @Description 用户信息
 * @Author sunshaoyu
 * @Date 2020年07月15日-13:09
 */
public class UserInfo {

    private String userAcct;
    /** 除了主数据源之外的当前动态数据源 **/
    private String userDataSource;

    public UserInfo(String userAcct, String userDataSource) {
        this.userAcct = userAcct;
        this.userDataSource = userDataSource;
    }

    public String getUserAcct() {
        return userAcct;
    }

    public void setUserAcct(String userAcct) {
        this.userAcct = userAcct;
    }

    public String getUserDataSource() {
        return userDataSource;
    }

    public void setUserDataSource(String userDataSource) {
        this.userDataSource = userDataSource;
    }
}
