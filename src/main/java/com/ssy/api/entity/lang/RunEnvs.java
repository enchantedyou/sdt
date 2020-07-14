package com.ssy.api.entity.lang;

/**
 * @Description 运行时环境
 * @Author sunshaoyu
 * @Date 2020年07月06日-14:25
 */
public class RunEnvs {

    private String trxnSeq;
    private String requestStartTime;
    private String requestParams;

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
}
