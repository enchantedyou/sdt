package com.ssy.api.entity.type.local;

import com.ssy.api.meta.defaults.Element;

import java.util.Map;

/**
 * @Description 用户信息维护复合类型
 * @Author sunshaoyu
 * @Date 2020/10/23-11:12
 */
public class SdMntUser {

    private String gitlabSession;
    private String originalPwd;
    private String userPwd;
    private String confirmPwd;

    public String getGitlabSession() {
        return gitlabSession;
    }

    public void setGitlabSession(String gitlabSession) {
        this.gitlabSession = gitlabSession;
    }

    public String getOriginalPwd() {
        return originalPwd;
    }

    public void setOriginalPwd(String originalPwd) {
        this.originalPwd = originalPwd;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }

    @Override
    public String toString() {
        return "SdMntUser{" +
                "gitlabSession='" + gitlabSession + '\'' +
                ", originalPwd='" + originalPwd + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", confirmPwd='" + confirmPwd + '\'' +
                '}';
    }
}
