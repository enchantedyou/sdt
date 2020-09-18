package com.ssy.api.entity.lang;

import java.io.Serializable;

/**
 * @Description 运行时环境
 * @Author sunshaoyu
 * @Date 2020年07月06日-14:25
 */
public class RunEnvs implements Serializable {

    private String trxnSeq;
    private String requestStartTime;
    private String requestParams;

    private int currentPage;
    private int pageSize;
    private long totalCount;

    public RunEnvs(String trxnSeq, String requestStartTime, int currentPage, int pageSize) {
        this.trxnSeq = trxnSeq;
        this.requestStartTime = requestStartTime;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public RunEnvs(String trxnSeq, String requestStartTime) {
        this.trxnSeq = trxnSeq;
        this.requestStartTime = requestStartTime;
    }

    public String getTrxnSeq() {
        return trxnSeq;
    }

    public void setTrxnSeq(String trxnSeq) {
        this.trxnSeq = trxnSeq;
    }

    public String getRequestStartTime() {
        return requestStartTime;
    }

    public void setRequestStartTime(String requestStartTime) {
        this.requestStartTime = requestStartTime;
    }

    public String getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
