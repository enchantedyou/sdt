package com.ssy.api.entity.table.ap;

import java.math.BigDecimal;

public class ApsAccountingEvent {
    private String trxnSeq;

    private Long dataSort;

    private String orgId;

    private String mainTrxnSeq;

    private String acctNo;

    private String subAcctSeq;

    private String trxnDate;

    private String doubleEntryInd;

    private String debitCredit;

    private String trxnCcy;

    private BigDecimal trxnAmt;

    private String acctBranch;

    private String accountingAlias;

    private String accountingSubject;

    private String balAttributes;

    private Long hashValue;

    private String dataSyncInd;

    private String dataSyncFileId;

    private String glRefCode;

    private String branchSettleInd;

    private String summaryCode;

    private String summaryName;

    private String prodId;

    private String custNo;

    private String trxnCode;

    private String remark;

    private String accrueType;

    private String oppAcctNo;

    private String outOppAcctNo;

    private String dataCreateTime;

    private String dataUpdateTime;

    private String dataCreateUser;

    private String dataUpdateUser;

    private Long dataVersion;

    public ApsAccountingEvent(String trxnSeq, Long dataSort, String orgId, String mainTrxnSeq, String acctNo, String subAcctSeq, String trxnDate, String doubleEntryInd, String debitCredit, String trxnCcy, BigDecimal trxnAmt, String acctBranch, String accountingAlias, String accountingSubject, String balAttributes, Long hashValue, String dataSyncInd, String dataSyncFileId, String glRefCode, String branchSettleInd, String summaryCode, String summaryName, String prodId, String custNo, String trxnCode, String remark, String accrueType, String oppAcctNo, String outOppAcctNo, String dataCreateTime, String dataUpdateTime, String dataCreateUser, String dataUpdateUser, Long dataVersion) {
        this.trxnSeq = trxnSeq;
        this.dataSort = dataSort;
        this.orgId = orgId;
        this.mainTrxnSeq = mainTrxnSeq;
        this.acctNo = acctNo;
        this.subAcctSeq = subAcctSeq;
        this.trxnDate = trxnDate;
        this.doubleEntryInd = doubleEntryInd;
        this.debitCredit = debitCredit;
        this.trxnCcy = trxnCcy;
        this.trxnAmt = trxnAmt;
        this.acctBranch = acctBranch;
        this.accountingAlias = accountingAlias;
        this.accountingSubject = accountingSubject;
        this.balAttributes = balAttributes;
        this.hashValue = hashValue;
        this.dataSyncInd = dataSyncInd;
        this.dataSyncFileId = dataSyncFileId;
        this.glRefCode = glRefCode;
        this.branchSettleInd = branchSettleInd;
        this.summaryCode = summaryCode;
        this.summaryName = summaryName;
        this.prodId = prodId;
        this.custNo = custNo;
        this.trxnCode = trxnCode;
        this.remark = remark;
        this.accrueType = accrueType;
        this.oppAcctNo = oppAcctNo;
        this.outOppAcctNo = outOppAcctNo;
        this.dataCreateTime = dataCreateTime;
        this.dataUpdateTime = dataUpdateTime;
        this.dataCreateUser = dataCreateUser;
        this.dataUpdateUser = dataUpdateUser;
        this.dataVersion = dataVersion;
    }

    public ApsAccountingEvent() {
        super();
    }

    public String getTrxnSeq() {
        return trxnSeq;
    }

    public void setTrxnSeq(String trxnSeq) {
        this.trxnSeq = trxnSeq == null ? null : trxnSeq.trim();
    }

    public Long getDataSort() {
        return dataSort;
    }

    public void setDataSort(Long dataSort) {
        this.dataSort = dataSort;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getMainTrxnSeq() {
        return mainTrxnSeq;
    }

    public void setMainTrxnSeq(String mainTrxnSeq) {
        this.mainTrxnSeq = mainTrxnSeq == null ? null : mainTrxnSeq.trim();
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo == null ? null : acctNo.trim();
    }

    public String getSubAcctSeq() {
        return subAcctSeq;
    }

    public void setSubAcctSeq(String subAcctSeq) {
        this.subAcctSeq = subAcctSeq == null ? null : subAcctSeq.trim();
    }

    public String getTrxnDate() {
        return trxnDate;
    }

    public void setTrxnDate(String trxnDate) {
        this.trxnDate = trxnDate == null ? null : trxnDate.trim();
    }

    public String getDoubleEntryInd() {
        return doubleEntryInd;
    }

    public void setDoubleEntryInd(String doubleEntryInd) {
        this.doubleEntryInd = doubleEntryInd == null ? null : doubleEntryInd.trim();
    }

    public String getDebitCredit() {
        return debitCredit;
    }

    public void setDebitCredit(String debitCredit) {
        this.debitCredit = debitCredit == null ? null : debitCredit.trim();
    }

    public String getTrxnCcy() {
        return trxnCcy;
    }

    public void setTrxnCcy(String trxnCcy) {
        this.trxnCcy = trxnCcy == null ? null : trxnCcy.trim();
    }

    public BigDecimal getTrxnAmt() {
        return trxnAmt;
    }

    public void setTrxnAmt(BigDecimal trxnAmt) {
        this.trxnAmt = trxnAmt;
    }

    public String getAcctBranch() {
        return acctBranch;
    }

    public void setAcctBranch(String acctBranch) {
        this.acctBranch = acctBranch == null ? null : acctBranch.trim();
    }

    public String getAccountingAlias() {
        return accountingAlias;
    }

    public void setAccountingAlias(String accountingAlias) {
        this.accountingAlias = accountingAlias == null ? null : accountingAlias.trim();
    }

    public String getAccountingSubject() {
        return accountingSubject;
    }

    public void setAccountingSubject(String accountingSubject) {
        this.accountingSubject = accountingSubject == null ? null : accountingSubject.trim();
    }

    public String getBalAttributes() {
        return balAttributes;
    }

    public void setBalAttributes(String balAttributes) {
        this.balAttributes = balAttributes == null ? null : balAttributes.trim();
    }

    public Long getHashValue() {
        return hashValue;
    }

    public void setHashValue(Long hashValue) {
        this.hashValue = hashValue;
    }

    public String getDataSyncInd() {
        return dataSyncInd;
    }

    public void setDataSyncInd(String dataSyncInd) {
        this.dataSyncInd = dataSyncInd == null ? null : dataSyncInd.trim();
    }

    public String getDataSyncFileId() {
        return dataSyncFileId;
    }

    public void setDataSyncFileId(String dataSyncFileId) {
        this.dataSyncFileId = dataSyncFileId == null ? null : dataSyncFileId.trim();
    }

    public String getGlRefCode() {
        return glRefCode;
    }

    public void setGlRefCode(String glRefCode) {
        this.glRefCode = glRefCode == null ? null : glRefCode.trim();
    }

    public String getBranchSettleInd() {
        return branchSettleInd;
    }

    public void setBranchSettleInd(String branchSettleInd) {
        this.branchSettleInd = branchSettleInd == null ? null : branchSettleInd.trim();
    }

    public String getSummaryCode() {
        return summaryCode;
    }

    public void setSummaryCode(String summaryCode) {
        this.summaryCode = summaryCode == null ? null : summaryCode.trim();
    }

    public String getSummaryName() {
        return summaryName;
    }

    public void setSummaryName(String summaryName) {
        this.summaryName = summaryName == null ? null : summaryName.trim();
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo == null ? null : custNo.trim();
    }

    public String getTrxnCode() {
        return trxnCode;
    }

    public void setTrxnCode(String trxnCode) {
        this.trxnCode = trxnCode == null ? null : trxnCode.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getAccrueType() {
        return accrueType;
    }

    public void setAccrueType(String accrueType) {
        this.accrueType = accrueType == null ? null : accrueType.trim();
    }

    public String getOppAcctNo() {
        return oppAcctNo;
    }

    public void setOppAcctNo(String oppAcctNo) {
        this.oppAcctNo = oppAcctNo == null ? null : oppAcctNo.trim();
    }

    public String getOutOppAcctNo() {
        return outOppAcctNo;
    }

    public void setOutOppAcctNo(String outOppAcctNo) {
        this.outOppAcctNo = outOppAcctNo == null ? null : outOppAcctNo.trim();
    }

    public String getDataCreateTime() {
        return dataCreateTime;
    }

    public void setDataCreateTime(String dataCreateTime) {
        this.dataCreateTime = dataCreateTime == null ? null : dataCreateTime.trim();
    }

    public String getDataUpdateTime() {
        return dataUpdateTime;
    }

    public void setDataUpdateTime(String dataUpdateTime) {
        this.dataUpdateTime = dataUpdateTime == null ? null : dataUpdateTime.trim();
    }

    public String getDataCreateUser() {
        return dataCreateUser;
    }

    public void setDataCreateUser(String dataCreateUser) {
        this.dataCreateUser = dataCreateUser == null ? null : dataCreateUser.trim();
    }

    public String getDataUpdateUser() {
        return dataUpdateUser;
    }

    public void setDataUpdateUser(String dataUpdateUser) {
        this.dataUpdateUser = dataUpdateUser == null ? null : dataUpdateUser.trim();
    }

    public Long getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(Long dataVersion) {
        this.dataVersion = dataVersion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", trxnSeq=").append(trxnSeq);
        sb.append(", dataSort=").append(dataSort);
        sb.append(", orgId=").append(orgId);
        sb.append(", mainTrxnSeq=").append(mainTrxnSeq);
        sb.append(", acctNo=").append(acctNo);
        sb.append(", subAcctSeq=").append(subAcctSeq);
        sb.append(", trxnDate=").append(trxnDate);
        sb.append(", doubleEntryInd=").append(doubleEntryInd);
        sb.append(", debitCredit=").append(debitCredit);
        sb.append(", trxnCcy=").append(trxnCcy);
        sb.append(", trxnAmt=").append(trxnAmt);
        sb.append(", acctBranch=").append(acctBranch);
        sb.append(", accountingAlias=").append(accountingAlias);
        sb.append(", accountingSubject=").append(accountingSubject);
        sb.append(", balAttributes=").append(balAttributes);
        sb.append(", hashValue=").append(hashValue);
        sb.append(", dataSyncInd=").append(dataSyncInd);
        sb.append(", dataSyncFileId=").append(dataSyncFileId);
        sb.append(", glRefCode=").append(glRefCode);
        sb.append(", branchSettleInd=").append(branchSettleInd);
        sb.append(", summaryCode=").append(summaryCode);
        sb.append(", summaryName=").append(summaryName);
        sb.append(", prodId=").append(prodId);
        sb.append(", custNo=").append(custNo);
        sb.append(", trxnCode=").append(trxnCode);
        sb.append(", remark=").append(remark);
        sb.append(", accrueType=").append(accrueType);
        sb.append(", oppAcctNo=").append(oppAcctNo);
        sb.append(", outOppAcctNo=").append(outOppAcctNo);
        sb.append(", dataCreateTime=").append(dataCreateTime);
        sb.append(", dataUpdateTime=").append(dataUpdateTime);
        sb.append(", dataCreateUser=").append(dataCreateUser);
        sb.append(", dataUpdateUser=").append(dataUpdateUser);
        sb.append(", dataVersion=").append(dataVersion);
        sb.append("]");
        return sb.toString();
    }
}