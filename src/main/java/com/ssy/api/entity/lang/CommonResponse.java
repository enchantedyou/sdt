package com.ssy.api.entity.lang;

/**
 * @Description 公共响应区
 * @Author sunshaoyu
 * @Date 2020年07月14日-14:55
 */
public class CommonResponse {

    private String trxnSeq;
    private String responseTime;

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
}
