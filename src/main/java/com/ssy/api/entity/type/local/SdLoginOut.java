package com.ssy.api.entity.type.local;

/**
 * @Description
 * @Author sunshaoyu
 * @Date 2020年07月11日-14:14
 */
public class SdLoginOut {

    private String loginTime;
    private String loginIp;

    public SdLoginOut(String loginTime, String loginIp) {
        this.loginTime = loginTime;
        this.loginIp = loginIp;
    }

    public SdLoginOut(){

    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    @Override
    public String toString() {
        return "SdLoginOut{" +
                "loginTime='" + loginTime + '\'' +
                ", loginIp='" + loginIp + '\'' +
                '}';
    }
}
