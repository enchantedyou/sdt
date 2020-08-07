package com.ssy.api.entity.lang;

/**
 * @Description 公共响应区
 * @Author sunshaoyu
 * @Date 2020年07月14日-14:55
 */
public class CommonResponse {

    private String trxnSeq;
    private String responseTime;
    private int currentPage;
    private int pageSize;
    private long totalCount;

    public CommonResponse(String trxnSeq, String responseTime, int currentPage, int pageSize, long totalCount) {
        this.trxnSeq = trxnSeq;
        this.responseTime = responseTime;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }

    public CommonResponse(String trxnSeq, String responseTime) {
        this.trxnSeq = trxnSeq;
        this.responseTime = responseTime;
    }

    public String getTrxnSeq() {
        return trxnSeq;
    }

    public void setTrxnSeq(String trxnSeq) {
        this.trxnSeq = trxnSeq;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
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

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
