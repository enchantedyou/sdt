package com.ssy.api.entity.table.ln;

import java.math.BigDecimal;

public class LnaRepaymentSchedule {
    private String loanNo;

    private Long periodNo;

    private Long subPeriodNo;

    private String orgId;

    private Long phaseNo;

    private String dueDate;

    private String standardDueDate;

    private String graceDueDate;

    private BigDecimal normalInterestRate;

    private BigDecimal accrualPrcp;

    private BigDecimal installment;

    private BigDecimal periodPrcp;

    private BigDecimal periodInterest;

    private BigDecimal earlyRpymPrincipal;

    private BigDecimal totalPenaltyInterest;

    private BigDecimal totalCompoundInterest;

    private BigDecimal principal;

    private BigDecimal accrualInterest;

    private BigDecimal dueInterest;

    private BigDecimal accrualPenaltyInterest;

    private BigDecimal duePenaltyInterest;

    private BigDecimal accrualCompInterest;

    private BigDecimal dueCompInterest;

    private BigDecimal oweOverdueFee;

    private BigDecimal oweRpymfailFee;

    private BigDecimal oweAdvanceFee;

    private Long loanClassification;

    private String dueInd;

    private String settlInd;

    private Long serialNo;

    private String dataCreateTime;

    private String dataUpdateTime;

    private String dataCreateUser;

    private String dataUpdateUser;

    private Long dataVersion;

    public LnaRepaymentSchedule(String loanNo, Long periodNo, Long subPeriodNo, String orgId, Long phaseNo, String dueDate, String standardDueDate, String graceDueDate, BigDecimal normalInterestRate, BigDecimal accrualPrcp, BigDecimal installment, BigDecimal periodPrcp, BigDecimal periodInterest, BigDecimal earlyRpymPrincipal, BigDecimal totalPenaltyInterest, BigDecimal totalCompoundInterest, BigDecimal principal, BigDecimal accrualInterest, BigDecimal dueInterest, BigDecimal accrualPenaltyInterest, BigDecimal duePenaltyInterest, BigDecimal accrualCompInterest, BigDecimal dueCompInterest, BigDecimal oweOverdueFee, BigDecimal oweRpymfailFee, BigDecimal oweAdvanceFee, Long loanClassification, String dueInd, String settlInd, Long serialNo, String dataCreateTime, String dataUpdateTime, String dataCreateUser, String dataUpdateUser, Long dataVersion) {
        this.loanNo = loanNo;
        this.periodNo = periodNo;
        this.subPeriodNo = subPeriodNo;
        this.orgId = orgId;
        this.phaseNo = phaseNo;
        this.dueDate = dueDate;
        this.standardDueDate = standardDueDate;
        this.graceDueDate = graceDueDate;
        this.normalInterestRate = normalInterestRate;
        this.accrualPrcp = accrualPrcp;
        this.installment = installment;
        this.periodPrcp = periodPrcp;
        this.periodInterest = periodInterest;
        this.earlyRpymPrincipal = earlyRpymPrincipal;
        this.totalPenaltyInterest = totalPenaltyInterest;
        this.totalCompoundInterest = totalCompoundInterest;
        this.principal = principal;
        this.accrualInterest = accrualInterest;
        this.dueInterest = dueInterest;
        this.accrualPenaltyInterest = accrualPenaltyInterest;
        this.duePenaltyInterest = duePenaltyInterest;
        this.accrualCompInterest = accrualCompInterest;
        this.dueCompInterest = dueCompInterest;
        this.oweOverdueFee = oweOverdueFee;
        this.oweRpymfailFee = oweRpymfailFee;
        this.oweAdvanceFee = oweAdvanceFee;
        this.loanClassification = loanClassification;
        this.dueInd = dueInd;
        this.settlInd = settlInd;
        this.serialNo = serialNo;
        this.dataCreateTime = dataCreateTime;
        this.dataUpdateTime = dataUpdateTime;
        this.dataCreateUser = dataCreateUser;
        this.dataUpdateUser = dataUpdateUser;
        this.dataVersion = dataVersion;
    }

    public LnaRepaymentSchedule() {
        super();
    }

    public String getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo == null ? null : loanNo.trim();
    }

    public Long getPeriodNo() {
        return periodNo;
    }

    public void setPeriodNo(Long periodNo) {
        this.periodNo = periodNo;
    }

    public Long getSubPeriodNo() {
        return subPeriodNo;
    }

    public void setSubPeriodNo(Long subPeriodNo) {
        this.subPeriodNo = subPeriodNo;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public Long getPhaseNo() {
        return phaseNo;
    }

    public void setPhaseNo(Long phaseNo) {
        this.phaseNo = phaseNo;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate == null ? null : dueDate.trim();
    }

    public String getStandardDueDate() {
        return standardDueDate;
    }

    public void setStandardDueDate(String standardDueDate) {
        this.standardDueDate = standardDueDate == null ? null : standardDueDate.trim();
    }

    public String getGraceDueDate() {
        return graceDueDate;
    }

    public void setGraceDueDate(String graceDueDate) {
        this.graceDueDate = graceDueDate == null ? null : graceDueDate.trim();
    }

    public BigDecimal getNormalInterestRate() {
        return normalInterestRate;
    }

    public void setNormalInterestRate(BigDecimal normalInterestRate) {
        this.normalInterestRate = normalInterestRate;
    }

    public BigDecimal getAccrualPrcp() {
        return accrualPrcp;
    }

    public void setAccrualPrcp(BigDecimal accrualPrcp) {
        this.accrualPrcp = accrualPrcp;
    }

    public BigDecimal getInstallment() {
        return installment;
    }

    public void setInstallment(BigDecimal installment) {
        this.installment = installment;
    }

    public BigDecimal getPeriodPrcp() {
        return periodPrcp;
    }

    public void setPeriodPrcp(BigDecimal periodPrcp) {
        this.periodPrcp = periodPrcp;
    }

    public BigDecimal getPeriodInterest() {
        return periodInterest;
    }

    public void setPeriodInterest(BigDecimal periodInterest) {
        this.periodInterest = periodInterest;
    }

    public BigDecimal getEarlyRpymPrincipal() {
        return earlyRpymPrincipal;
    }

    public void setEarlyRpymPrincipal(BigDecimal earlyRpymPrincipal) {
        this.earlyRpymPrincipal = earlyRpymPrincipal;
    }

    public BigDecimal getTotalPenaltyInterest() {
        return totalPenaltyInterest;
    }

    public void setTotalPenaltyInterest(BigDecimal totalPenaltyInterest) {
        this.totalPenaltyInterest = totalPenaltyInterest;
    }

    public BigDecimal getTotalCompoundInterest() {
        return totalCompoundInterest;
    }

    public void setTotalCompoundInterest(BigDecimal totalCompoundInterest) {
        this.totalCompoundInterest = totalCompoundInterest;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    public BigDecimal getAccrualInterest() {
        return accrualInterest;
    }

    public void setAccrualInterest(BigDecimal accrualInterest) {
        this.accrualInterest = accrualInterest;
    }

    public BigDecimal getDueInterest() {
        return dueInterest;
    }

    public void setDueInterest(BigDecimal dueInterest) {
        this.dueInterest = dueInterest;
    }

    public BigDecimal getAccrualPenaltyInterest() {
        return accrualPenaltyInterest;
    }

    public void setAccrualPenaltyInterest(BigDecimal accrualPenaltyInterest) {
        this.accrualPenaltyInterest = accrualPenaltyInterest;
    }

    public BigDecimal getDuePenaltyInterest() {
        return duePenaltyInterest;
    }

    public void setDuePenaltyInterest(BigDecimal duePenaltyInterest) {
        this.duePenaltyInterest = duePenaltyInterest;
    }

    public BigDecimal getAccrualCompInterest() {
        return accrualCompInterest;
    }

    public void setAccrualCompInterest(BigDecimal accrualCompInterest) {
        this.accrualCompInterest = accrualCompInterest;
    }

    public BigDecimal getDueCompInterest() {
        return dueCompInterest;
    }

    public void setDueCompInterest(BigDecimal dueCompInterest) {
        this.dueCompInterest = dueCompInterest;
    }

    public BigDecimal getOweOverdueFee() {
        return oweOverdueFee;
    }

    public void setOweOverdueFee(BigDecimal oweOverdueFee) {
        this.oweOverdueFee = oweOverdueFee;
    }

    public BigDecimal getOweRpymfailFee() {
        return oweRpymfailFee;
    }

    public void setOweRpymfailFee(BigDecimal oweRpymfailFee) {
        this.oweRpymfailFee = oweRpymfailFee;
    }

    public BigDecimal getOweAdvanceFee() {
        return oweAdvanceFee;
    }

    public void setOweAdvanceFee(BigDecimal oweAdvanceFee) {
        this.oweAdvanceFee = oweAdvanceFee;
    }

    public Long getLoanClassification() {
        return loanClassification;
    }

    public void setLoanClassification(Long loanClassification) {
        this.loanClassification = loanClassification;
    }

    public String getDueInd() {
        return dueInd;
    }

    public void setDueInd(String dueInd) {
        this.dueInd = dueInd == null ? null : dueInd.trim();
    }

    public String getSettlInd() {
        return settlInd;
    }

    public void setSettlInd(String settlInd) {
        this.settlInd = settlInd == null ? null : settlInd.trim();
    }

    public Long getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Long serialNo) {
        this.serialNo = serialNo;
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
        sb.append(", periodNo=").append(periodNo);
        sb.append(", subPeriodNo=").append(subPeriodNo);
        sb.append(", orgId=").append(orgId);
        sb.append(", phaseNo=").append(phaseNo);
        sb.append(", dueDate=").append(dueDate);
        sb.append(", standardDueDate=").append(standardDueDate);
        sb.append(", graceDueDate=").append(graceDueDate);
        sb.append(", normalInterestRate=").append(normalInterestRate);
        sb.append(", accrualPrcp=").append(accrualPrcp);
        sb.append(", installment=").append(installment);
        sb.append(", periodPrcp=").append(periodPrcp);
        sb.append(", periodInterest=").append(periodInterest);
        sb.append(", earlyRpymPrincipal=").append(earlyRpymPrincipal);
        sb.append(", totalPenaltyInterest=").append(totalPenaltyInterest);
        sb.append(", totalCompoundInterest=").append(totalCompoundInterest);
        sb.append(", principal=").append(principal);
        sb.append(", accrualInterest=").append(accrualInterest);
        sb.append(", dueInterest=").append(dueInterest);
        sb.append(", accrualPenaltyInterest=").append(accrualPenaltyInterest);
        sb.append(", duePenaltyInterest=").append(duePenaltyInterest);
        sb.append(", accrualCompInterest=").append(accrualCompInterest);
        sb.append(", dueCompInterest=").append(dueCompInterest);
        sb.append(", oweOverdueFee=").append(oweOverdueFee);
        sb.append(", oweRpymfailFee=").append(oweRpymfailFee);
        sb.append(", oweAdvanceFee=").append(oweAdvanceFee);
        sb.append(", loanClassification=").append(loanClassification);
        sb.append(", dueInd=").append(dueInd);
        sb.append(", settlInd=").append(settlInd);
        sb.append(", serialNo=").append(serialNo);
        sb.append(", dataCreateTime=").append(dataCreateTime);
        sb.append(", dataUpdateTime=").append(dataUpdateTime);
        sb.append(", dataCreateUser=").append(dataCreateUser);
        sb.append(", dataUpdateUser=").append(dataUpdateUser);
        sb.append(", dataVersion=").append(dataVersion);
        sb.append("]");
        return sb.toString();
    }
}