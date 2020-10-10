package com.ssy.api.entity.table.ln;

import java.math.BigDecimal;

public class LnaLoan {
    private String loanNo;

    private String orgId;

    private String limitCode;

    private String ccyCode;

    private String prodId;

    private String custNo;

    private String custName;

    private String contractNo;

    private String loanCreationDate;

    private String startInstDate;

    private String maturityDate;

    private String originalMaturityDate;

    private String openBranch;

    private String acctBranch;

    private String loanTenor;

    private Long loanTenorGl;

    private Long totPeriods;

    private Long currentPeriodNo;

    private Long loanClassification;

    private String wrofInd;

    private String wrofDate;

    private String loanStatus;

    private String closeAcctDate;

    private String accountingAlias;

    private BigDecimal loanAmt;

    private BigDecimal alreadyDrawdownAmt;

    private BigDecimal availableDrawdownAmt;

    private BigDecimal outstandingPrcp;

    private BigDecimal duePrcp;

    private BigDecimal advanceInterest;

    private String balUpdateDate;

    private Long hashValue;

    private String attrValue;

    private String loanGlType;

    private String newContractInd;

    private String settlInd;

    private String settlDate;

    private String originalLoanNo;

    private String overdueInd;

    private BigDecimal initOldiAmt;

    private String intReversalInd;

    private Long alreadyReduceCount;

    private String reversalType;

    private String dataCreateTime;

    private String dataUpdateTime;

    private String dataCreateUser;

    private String dataUpdateUser;

    private Long dataVersion;

    public LnaLoan(String loanNo, String orgId, String limitCode, String ccyCode, String prodId, String custNo, String custName, String contractNo, String loanCreationDate, String startInstDate, String maturityDate, String originalMaturityDate, String openBranch, String acctBranch, String loanTenor, Long loanTenorGl, Long totPeriods, Long currentPeriodNo, Long loanClassification, String wrofInd, String wrofDate, String loanStatus, String closeAcctDate, String accountingAlias, BigDecimal loanAmt, BigDecimal alreadyDrawdownAmt, BigDecimal availableDrawdownAmt, BigDecimal outstandingPrcp, BigDecimal duePrcp, BigDecimal advanceInterest, String balUpdateDate, Long hashValue, String attrValue, String loanGlType, String newContractInd, String settlInd, String settlDate, String originalLoanNo, String overdueInd, BigDecimal initOldiAmt, String intReversalInd, Long alreadyReduceCount, String reversalType, String dataCreateTime, String dataUpdateTime, String dataCreateUser, String dataUpdateUser, Long dataVersion) {
        this.loanNo = loanNo;
        this.orgId = orgId;
        this.limitCode = limitCode;
        this.ccyCode = ccyCode;
        this.prodId = prodId;
        this.custNo = custNo;
        this.custName = custName;
        this.contractNo = contractNo;
        this.loanCreationDate = loanCreationDate;
        this.startInstDate = startInstDate;
        this.maturityDate = maturityDate;
        this.originalMaturityDate = originalMaturityDate;
        this.openBranch = openBranch;
        this.acctBranch = acctBranch;
        this.loanTenor = loanTenor;
        this.loanTenorGl = loanTenorGl;
        this.totPeriods = totPeriods;
        this.currentPeriodNo = currentPeriodNo;
        this.loanClassification = loanClassification;
        this.wrofInd = wrofInd;
        this.wrofDate = wrofDate;
        this.loanStatus = loanStatus;
        this.closeAcctDate = closeAcctDate;
        this.accountingAlias = accountingAlias;
        this.loanAmt = loanAmt;
        this.alreadyDrawdownAmt = alreadyDrawdownAmt;
        this.availableDrawdownAmt = availableDrawdownAmt;
        this.outstandingPrcp = outstandingPrcp;
        this.duePrcp = duePrcp;
        this.advanceInterest = advanceInterest;
        this.balUpdateDate = balUpdateDate;
        this.hashValue = hashValue;
        this.attrValue = attrValue;
        this.loanGlType = loanGlType;
        this.newContractInd = newContractInd;
        this.settlInd = settlInd;
        this.settlDate = settlDate;
        this.originalLoanNo = originalLoanNo;
        this.overdueInd = overdueInd;
        this.initOldiAmt = initOldiAmt;
        this.intReversalInd = intReversalInd;
        this.alreadyReduceCount = alreadyReduceCount;
        this.reversalType = reversalType;
        this.dataCreateTime = dataCreateTime;
        this.dataUpdateTime = dataUpdateTime;
        this.dataCreateUser = dataCreateUser;
        this.dataUpdateUser = dataUpdateUser;
        this.dataVersion = dataVersion;
    }

    public LnaLoan() {
        super();
    }

    public String getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo == null ? null : loanNo.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getLimitCode() {
        return limitCode;
    }

    public void setLimitCode(String limitCode) {
        this.limitCode = limitCode == null ? null : limitCode.trim();
    }

    public String getCcyCode() {
        return ccyCode;
    }

    public void setCcyCode(String ccyCode) {
        this.ccyCode = ccyCode == null ? null : ccyCode.trim();
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

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName == null ? null : custName.trim();
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    public String getLoanCreationDate() {
        return loanCreationDate;
    }

    public void setLoanCreationDate(String loanCreationDate) {
        this.loanCreationDate = loanCreationDate == null ? null : loanCreationDate.trim();
    }

    public String getStartInstDate() {
        return startInstDate;
    }

    public void setStartInstDate(String startInstDate) {
        this.startInstDate = startInstDate == null ? null : startInstDate.trim();
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate == null ? null : maturityDate.trim();
    }

    public String getOriginalMaturityDate() {
        return originalMaturityDate;
    }

    public void setOriginalMaturityDate(String originalMaturityDate) {
        this.originalMaturityDate = originalMaturityDate == null ? null : originalMaturityDate.trim();
    }

    public String getOpenBranch() {
        return openBranch;
    }

    public void setOpenBranch(String openBranch) {
        this.openBranch = openBranch == null ? null : openBranch.trim();
    }

    public String getAcctBranch() {
        return acctBranch;
    }

    public void setAcctBranch(String acctBranch) {
        this.acctBranch = acctBranch == null ? null : acctBranch.trim();
    }

    public String getLoanTenor() {
        return loanTenor;
    }

    public void setLoanTenor(String loanTenor) {
        this.loanTenor = loanTenor == null ? null : loanTenor.trim();
    }

    public Long getLoanTenorGl() {
        return loanTenorGl;
    }

    public void setLoanTenorGl(Long loanTenorGl) {
        this.loanTenorGl = loanTenorGl;
    }

    public Long getTotPeriods() {
        return totPeriods;
    }

    public void setTotPeriods(Long totPeriods) {
        this.totPeriods = totPeriods;
    }

    public Long getCurrentPeriodNo() {
        return currentPeriodNo;
    }

    public void setCurrentPeriodNo(Long currentPeriodNo) {
        this.currentPeriodNo = currentPeriodNo;
    }

    public Long getLoanClassification() {
        return loanClassification;
    }

    public void setLoanClassification(Long loanClassification) {
        this.loanClassification = loanClassification;
    }

    public String getWrofInd() {
        return wrofInd;
    }

    public void setWrofInd(String wrofInd) {
        this.wrofInd = wrofInd == null ? null : wrofInd.trim();
    }

    public String getWrofDate() {
        return wrofDate;
    }

    public void setWrofDate(String wrofDate) {
        this.wrofDate = wrofDate == null ? null : wrofDate.trim();
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus == null ? null : loanStatus.trim();
    }

    public String getCloseAcctDate() {
        return closeAcctDate;
    }

    public void setCloseAcctDate(String closeAcctDate) {
        this.closeAcctDate = closeAcctDate == null ? null : closeAcctDate.trim();
    }

    public String getAccountingAlias() {
        return accountingAlias;
    }

    public void setAccountingAlias(String accountingAlias) {
        this.accountingAlias = accountingAlias == null ? null : accountingAlias.trim();
    }

    public BigDecimal getLoanAmt() {
        return loanAmt;
    }

    public void setLoanAmt(BigDecimal loanAmt) {
        this.loanAmt = loanAmt;
    }

    public BigDecimal getAlreadyDrawdownAmt() {
        return alreadyDrawdownAmt;
    }

    public void setAlreadyDrawdownAmt(BigDecimal alreadyDrawdownAmt) {
        this.alreadyDrawdownAmt = alreadyDrawdownAmt;
    }

    public BigDecimal getAvailableDrawdownAmt() {
        return availableDrawdownAmt;
    }

    public void setAvailableDrawdownAmt(BigDecimal availableDrawdownAmt) {
        this.availableDrawdownAmt = availableDrawdownAmt;
    }

    public BigDecimal getOutstandingPrcp() {
        return outstandingPrcp;
    }

    public void setOutstandingPrcp(BigDecimal outstandingPrcp) {
        this.outstandingPrcp = outstandingPrcp;
    }

    public BigDecimal getDuePrcp() {
        return duePrcp;
    }

    public void setDuePrcp(BigDecimal duePrcp) {
        this.duePrcp = duePrcp;
    }

    public BigDecimal getAdvanceInterest() {
        return advanceInterest;
    }

    public void setAdvanceInterest(BigDecimal advanceInterest) {
        this.advanceInterest = advanceInterest;
    }

    public String getBalUpdateDate() {
        return balUpdateDate;
    }

    public void setBalUpdateDate(String balUpdateDate) {
        this.balUpdateDate = balUpdateDate == null ? null : balUpdateDate.trim();
    }

    public Long getHashValue() {
        return hashValue;
    }

    public void setHashValue(Long hashValue) {
        this.hashValue = hashValue;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue == null ? null : attrValue.trim();
    }

    public String getLoanGlType() {
        return loanGlType;
    }

    public void setLoanGlType(String loanGlType) {
        this.loanGlType = loanGlType == null ? null : loanGlType.trim();
    }

    public String getNewContractInd() {
        return newContractInd;
    }

    public void setNewContractInd(String newContractInd) {
        this.newContractInd = newContractInd == null ? null : newContractInd.trim();
    }

    public String getSettlInd() {
        return settlInd;
    }

    public void setSettlInd(String settlInd) {
        this.settlInd = settlInd == null ? null : settlInd.trim();
    }

    public String getSettlDate() {
        return settlDate;
    }

    public void setSettlDate(String settlDate) {
        this.settlDate = settlDate == null ? null : settlDate.trim();
    }

    public String getOriginalLoanNo() {
        return originalLoanNo;
    }

    public void setOriginalLoanNo(String originalLoanNo) {
        this.originalLoanNo = originalLoanNo == null ? null : originalLoanNo.trim();
    }

    public String getOverdueInd() {
        return overdueInd;
    }

    public void setOverdueInd(String overdueInd) {
        this.overdueInd = overdueInd == null ? null : overdueInd.trim();
    }

    public BigDecimal getInitOldiAmt() {
        return initOldiAmt;
    }

    public void setInitOldiAmt(BigDecimal initOldiAmt) {
        this.initOldiAmt = initOldiAmt;
    }

    public String getIntReversalInd() {
        return intReversalInd;
    }

    public void setIntReversalInd(String intReversalInd) {
        this.intReversalInd = intReversalInd == null ? null : intReversalInd.trim();
    }

    public Long getAlreadyReduceCount() {
        return alreadyReduceCount;
    }

    public void setAlreadyReduceCount(Long alreadyReduceCount) {
        this.alreadyReduceCount = alreadyReduceCount;
    }

    public String getReversalType() {
        return reversalType;
    }

    public void setReversalType(String reversalType) {
        this.reversalType = reversalType == null ? null : reversalType.trim();
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
        sb.append(", loanNo=").append(loanNo);
        sb.append(", orgId=").append(orgId);
        sb.append(", limitCode=").append(limitCode);
        sb.append(", ccyCode=").append(ccyCode);
        sb.append(", prodId=").append(prodId);
        sb.append(", custNo=").append(custNo);
        sb.append(", custName=").append(custName);
        sb.append(", contractNo=").append(contractNo);
        sb.append(", loanCreationDate=").append(loanCreationDate);
        sb.append(", startInstDate=").append(startInstDate);
        sb.append(", maturityDate=").append(maturityDate);
        sb.append(", originalMaturityDate=").append(originalMaturityDate);
        sb.append(", openBranch=").append(openBranch);
        sb.append(", acctBranch=").append(acctBranch);
        sb.append(", loanTenor=").append(loanTenor);
        sb.append(", loanTenorGl=").append(loanTenorGl);
        sb.append(", totPeriods=").append(totPeriods);
        sb.append(", currentPeriodNo=").append(currentPeriodNo);
        sb.append(", loanClassification=").append(loanClassification);
        sb.append(", wrofInd=").append(wrofInd);
        sb.append(", wrofDate=").append(wrofDate);
        sb.append(", loanStatus=").append(loanStatus);
        sb.append(", closeAcctDate=").append(closeAcctDate);
        sb.append(", accountingAlias=").append(accountingAlias);
        sb.append(", loanAmt=").append(loanAmt);
        sb.append(", alreadyDrawdownAmt=").append(alreadyDrawdownAmt);
        sb.append(", availableDrawdownAmt=").append(availableDrawdownAmt);
        sb.append(", outstandingPrcp=").append(outstandingPrcp);
        sb.append(", duePrcp=").append(duePrcp);
        sb.append(", advanceInterest=").append(advanceInterest);
        sb.append(", balUpdateDate=").append(balUpdateDate);
        sb.append(", hashValue=").append(hashValue);
        sb.append(", attrValue=").append(attrValue);
        sb.append(", loanGlType=").append(loanGlType);
        sb.append(", newContractInd=").append(newContractInd);
        sb.append(", settlInd=").append(settlInd);
        sb.append(", settlDate=").append(settlDate);
        sb.append(", originalLoanNo=").append(originalLoanNo);
        sb.append(", overdueInd=").append(overdueInd);
        sb.append(", initOldiAmt=").append(initOldiAmt);
        sb.append(", intReversalInd=").append(intReversalInd);
        sb.append(", alreadyReduceCount=").append(alreadyReduceCount);
        sb.append(", reversalType=").append(reversalType);
        sb.append(", dataCreateTime=").append(dataCreateTime);
        sb.append(", dataUpdateTime=").append(dataUpdateTime);
        sb.append(", dataCreateUser=").append(dataCreateUser);
        sb.append(", dataUpdateUser=").append(dataUpdateUser);
        sb.append(", dataVersion=").append(dataVersion);
        sb.append("]");
        return sb.toString();
    }
}