package com.ssy.api.entity.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * @Description
 * @Author sunshaoyu
 * @Date 2020年06月13日-15:57
 */
@Component
@ConfigurationProperties(prefix = "sdt.context")
@Validated
public class SdtContextConfig {

    private String workSpacePath;
    private String intfExcelPath;
    private Boolean msModelFirst;

    public String getWorkSpacePath() {
        return workSpacePath;
    }

    public void setWorkSpacePath(String workSpacePath) {
        this.workSpacePath = workSpacePath;
    }

    public String getIntfExcelPath() {
        return intfExcelPath;
    }

    public void setIntfExcelPath(String intfExcelPath) {
        this.intfExcelPath = intfExcelPath;
    }

    public Boolean getMsModelFirst() {
        return msModelFirst;
    }

    public void setMsModelFirst(Boolean msModelFirst) {
        this.msModelFirst = msModelFirst;
    }
}
