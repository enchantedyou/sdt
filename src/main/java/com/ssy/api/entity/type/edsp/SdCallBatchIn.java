package com.ssy.api.entity.type.edsp;

import com.ssy.api.entity.enums.E_BATCHCALLMODE;

/**
 * @Description 唤醒批量输入复合类型
 * @Author sunshaoyu
 * @Date 2020年07月05日-15:53
 */
public class SdCallBatchIn {

    private E_BATCHCALLMODE batchCallMode;
    private String flowGroup;
    private String endDate;
    private int assignDays;
    private String batchRunNo;

    public E_BATCHCALLMODE getBatchCallMode() {
        return batchCallMode;
    }

    public void setBatchCallMode(E_BATCHCALLMODE batchCallMode) {
        this.batchCallMode = batchCallMode;
    }

    public String getFlowGroup() {
        return flowGroup;
    }

    public void setFlowGroup(String flowGroup) {
        this.flowGroup = flowGroup;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getAssignDays() {
        return assignDays;
    }

    public void setAssignDays(int assignDays) {
        this.assignDays = assignDays;
    }

    public String getBatchRunNo() {
        return batchRunNo;
    }

    public void setBatchRunNo(String batchRunNo) {
        this.batchRunNo = batchRunNo;
    }
}
