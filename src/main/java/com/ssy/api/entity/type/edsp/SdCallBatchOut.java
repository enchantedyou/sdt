package com.ssy.api.entity.type.edsp;

import com.ssy.api.entity.enums.E_BATCHEXESTATUS;

/**
 * @Description 唤醒批量输入复合类型
 * @Author sunshaoyu
 * @Date 2020/7/5-19:42
 */
public class SdCallBatchOut {

    private String systemCode;
    private String busiOrgId;
    private String tranFlowId;
    private String trxnDate;

    private String progressRate;
    private E_BATCHEXESTATUS batchExeStatus;
    private String errorMessage;
    private String tranGroupId;

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getBusiOrgId() {
        return busiOrgId;
    }

    public void setBusiOrgId(String busiOrgId) {
        this.busiOrgId = busiOrgId;
    }

    public String getTranFlowId() {
        return tranFlowId;
    }

    public void setTranFlowId(String tranFlowId) {
        this.tranFlowId = tranFlowId;
    }

    public String getTrxnDate() {
        return trxnDate;
    }

    public void setTrxnDate(String trxnDate) {
        this.trxnDate = trxnDate;
    }

    public String getProgressRate() {
        return progressRate;
    }

    public void setProgressRate(String progressRate) {
        this.progressRate = progressRate;
    }

    public E_BATCHEXESTATUS getBatchExeStatus() {
        return batchExeStatus;
    }

    public void setBatchExeStatus(E_BATCHEXESTATUS batchExeStatus) {
        this.batchExeStatus = batchExeStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getTranGroupId() {
        return tranGroupId;
    }

    public void setTranGroupId(String tranGroupId) {
        this.tranGroupId = tranGroupId;
    }
}
