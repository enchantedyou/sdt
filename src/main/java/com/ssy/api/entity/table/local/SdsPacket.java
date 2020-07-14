package com.ssy.api.entity.table.local;

public class SdsPacket {
    private String trxnSeq;

    private String trxnDate;

    private String trxnDesc;

    private String beginTime;

    private String endTime;

    private String usedTime;

    private String errorText;

    private String hostIp;

    private String dataCreateUser;

    private String dataCreateTime;

    private String dataUpdateUser;

    private String dataUpdateTime;

    private Integer dataVersion;

    private String request;

    private String response;

    private String errorStack;

    public SdsPacket(String trxnSeq, String trxnDate, String trxnDesc, String beginTime, String endTime, String usedTime, String errorText, String hostIp, String dataCreateUser, String dataCreateTime, String dataUpdateUser, String dataUpdateTime, Integer dataVersion) {
        this.trxnSeq = trxnSeq;
        this.trxnDate = trxnDate;
        this.trxnDesc = trxnDesc;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.usedTime = usedTime;
        this.errorText = errorText;
        this.hostIp = hostIp;
        this.dataCreateUser = dataCreateUser;
        this.dataCreateTime = dataCreateTime;
        this.dataUpdateUser = dataUpdateUser;
        this.dataUpdateTime = dataUpdateTime;
        this.dataVersion = dataVersion;
    }

    public SdsPacket(String trxnSeq, String trxnDate, String trxnDesc, String beginTime, String endTime, String usedTime, String errorText, String hostIp, String dataCreateUser, String dataCreateTime, String dataUpdateUser, String dataUpdateTime, Integer dataVersion, String request, String response, String errorStack) {
        this.trxnSeq = trxnSeq;
        this.trxnDate = trxnDate;
        this.trxnDesc = trxnDesc;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.usedTime = usedTime;
        this.errorText = errorText;
        this.hostIp = hostIp;
        this.dataCreateUser = dataCreateUser;
        this.dataCreateTime = dataCreateTime;
        this.dataUpdateUser = dataUpdateUser;
        this.dataUpdateTime = dataUpdateTime;
        this.dataVersion = dataVersion;
        this.request = request;
        this.response = response;
        this.errorStack = errorStack;
    }

    public SdsPacket() {
        super();
    }

    public String getTrxnSeq() {
        return trxnSeq;
    }

    public void setTrxnSeq(String trxnSeq) {
        this.trxnSeq = trxnSeq == null ? null : trxnSeq.trim();
    }

    public String getTrxnDate() {
        return trxnDate;
    }

    public void setTrxnDate(String trxnDate) {
        this.trxnDate = trxnDate == null ? null : trxnDate.trim();
    }

    public String getTrxnDesc() {
        return trxnDesc;
    }

    public void setTrxnDesc(String trxnDesc) {
        this.trxnDesc = trxnDesc == null ? null : trxnDesc.trim();
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime == null ? null : beginTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public String getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(String usedTime) {
        this.usedTime = usedTime == null ? null : usedTime.trim();
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText == null ? null : errorText.trim();
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp == null ? null : hostIp.trim();
    }

    public String getDataCreateUser() {
        return dataCreateUser;
    }

    public void setDataCreateUser(String dataCreateUser) {
        this.dataCreateUser = dataCreateUser == null ? null : dataCreateUser.trim();
    }

    public String getDataCreateTime() {
        return dataCreateTime;
    }

    public void setDataCreateTime(String dataCreateTime) {
        this.dataCreateTime = dataCreateTime == null ? null : dataCreateTime.trim();
    }

    public String getDataUpdateUser() {
        return dataUpdateUser;
    }

    public void setDataUpdateUser(String dataUpdateUser) {
        this.dataUpdateUser = dataUpdateUser == null ? null : dataUpdateUser.trim();
    }

    public String getDataUpdateTime() {
        return dataUpdateTime;
    }

    public void setDataUpdateTime(String dataUpdateTime) {
        this.dataUpdateTime = dataUpdateTime == null ? null : dataUpdateTime.trim();
    }

    public Integer getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(Integer dataVersion) {
        this.dataVersion = dataVersion;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request == null ? null : request.trim();
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response == null ? null : response.trim();
    }

    public String getErrorStack() {
        return errorStack;
    }

    public void setErrorStack(String errorStack) {
        this.errorStack = errorStack == null ? null : errorStack.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", trxnSeq=").append(trxnSeq);
        sb.append(", trxnDate=").append(trxnDate);
        sb.append(", trxnDesc=").append(trxnDesc);
        sb.append(", beginTime=").append(beginTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", usedTime=").append(usedTime);
        sb.append(", errorText=").append(errorText);
        sb.append(", hostIp=").append(hostIp);
        sb.append(", dataCreateUser=").append(dataCreateUser);
        sb.append(", dataCreateTime=").append(dataCreateTime);
        sb.append(", dataUpdateUser=").append(dataUpdateUser);
        sb.append(", dataUpdateTime=").append(dataUpdateTime);
        sb.append(", dataVersion=").append(dataVersion);
        sb.append(", request=").append(request);
        sb.append(", response=").append(response);
        sb.append(", errorStack=").append(errorStack);
        sb.append("]");
        return sb.toString();
    }
}