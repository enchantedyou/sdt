package com.ssy.api.entity.config;

import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Description
 * @Author sunshaoyu
 * @Date 2020年06月13日-15:57
 */
@Component
@ConfigurationProperties(prefix = "sdt.context")
@Validated
public class SdtContextConfig {

    /** icore3.0及以上项目的工作空间路径 **/
    @NotBlank private String workSpacePath;
    /** 接口文档目录 **/
    @NotBlank private String intfExcelPath;

    /** sump资源路径 **/
    @NotNull private Boolean msModelFirst;
    /** 是否支持微服务模型优先(MsDict,MsEnumType,MsType) **/
    @NotBlank private String sumpResourcePath;

    /** 默认交易日期 **/
    @NotBlank private String defaultTrxnDate;
    /** 批量任务轮询间隔(毫秒) **/
    @NotNull @Range(min = 1000L, max = 30000L) private Long batchPollInterval;

    /** 批量任务渠道号 **/
    @NotBlank private String batchChannelId;
    /** 日终批量任务执行域代码 **/
    @NotBlank private String eodDomainCode;

    /** 批量任务执行表无数据时的最大等待次数,等待时间(秒)=等待次数*轮询间隔 **/
    @NotNull @Range(min = 10, max = 30) private Integer batchMaxWaitCount;
    /** 业务法人代码 **/
    @NotBlank private String busiOrgId;

    /** 批量任务轮询线程池大小 **/
    @NotNull @Range(min = 1, max = 200) private int batchPollThreadPoolSize;
    /** gitlab session,用于操作git,获取合并文件 **/
    @NotBlank private String gitlabSession;

    /** 公共脚本主目录 **/
    private String commonSqlMainDir;
    /** 业务模块脚本主目录 **/
    private String moduleSqlMainDir;

    /** 稽核工具脚本目录 **/
    private String sqlToolsDir;
    /** 默认的sump数据源,用于生产接口文档 **/
    @NotBlank private String sumpDataSource;

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

    public String getSumpResourcePath() {
        return sumpResourcePath;
    }

    public void setSumpResourcePath(String sumpResourcePath) {
        this.sumpResourcePath = sumpResourcePath;
    }

    public String getDefaultTrxnDate() {
        return defaultTrxnDate;
    }

    public void setDefaultTrxnDate(String defaultTrxnDate) {
        this.defaultTrxnDate = defaultTrxnDate;
    }

    public Long getBatchPollInterval() {
        return batchPollInterval;
    }

    public void setBatchPollInterval(Long batchPollInterval) {
        this.batchPollInterval = batchPollInterval;
    }

    public String getBatchChannelId() {
        return batchChannelId;
    }

    public void setBatchChannelId(String batchChannelId) {
        this.batchChannelId = batchChannelId;
    }

    public String getEodDomainCode() {
        return eodDomainCode;
    }

    public void setEodDomainCode(String eodDomainCode) {
        this.eodDomainCode = eodDomainCode;
    }

    public Integer getBatchMaxWaitCount() {
        return batchMaxWaitCount;
    }

    public void setBatchMaxWaitCount(Integer batchMaxWaitCount) {
        this.batchMaxWaitCount = batchMaxWaitCount;
    }

    public String getBusiOrgId() {
        return busiOrgId;
    }

    public void setBusiOrgId(String busiOrgId) {
        this.busiOrgId = busiOrgId;
    }

    public int getBatchPollThreadPoolSize() {
        return batchPollThreadPoolSize;
    }

    public void setBatchPollThreadPoolSize(int batchPollThreadPoolSize) {
        this.batchPollThreadPoolSize = batchPollThreadPoolSize;
    }

    public String getGitlabSession() {
        return gitlabSession;
    }

    public void setGitlabSession(String gitlabSession) {
        this.gitlabSession = gitlabSession;
    }

    public String getCommonSqlMainDir() {
        return commonSqlMainDir;
    }

    public void setCommonSqlMainDir(String commonSqlMainDir) {
        this.commonSqlMainDir = commonSqlMainDir;
    }

    public String getModuleSqlMainDir() {
        return moduleSqlMainDir;
    }

    public void setModuleSqlMainDir(String moduleSqlMainDir) {
        this.moduleSqlMainDir = moduleSqlMainDir;
    }

    public String getSqlToolsDir() {
        return sqlToolsDir;
    }

    public void setSqlToolsDir(String sqlToolsDir) {
        this.sqlToolsDir = sqlToolsDir;
    }

    public String getSumpDataSource() {
        return sumpDataSource;
    }

    public void setSumpDataSource(String sumpDataSource) {
        this.sumpDataSource = sumpDataSource;
    }
}