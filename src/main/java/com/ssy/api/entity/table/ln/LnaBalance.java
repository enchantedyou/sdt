package com.ssy.api.entity.table.ln;

import java.math.BigDecimal;

public class LnaBalance {
    private String loanNo;

    private String balAttributes;

    private String orgId;

    private BigDecimal balanceAmount;

    private BigDecimal lastBalance;

    private String balCode;

    private String balAttributesGl;

    private String dataCreateTime;

    private String dataUpdateTime;

    private String dataCreateUser;

    private String dataUpdateUser;

    private Long dataVersion;

    public LnaBalance(String loanNo, String balAttributes, String orgId, BigDecimal balanceAmount, BigDecimal lastBalance, String balCode, String balAttributesGl, String dataCreateTime, String dataUpdateTime, String dataCreateUser, String dataUpdateUser, Long dataVersion) {
        this.loanNo = loanNo;
        this.balAttributes = balAttributes;
        this.orgId = orgId;
        this.balanceAmount = balanceAmount;
        this.lastBalance = lastBalance;
        this.balCode = balCode;
        this.balAttributesGl = balAttributesGl;
        this.dataCreateTime = dataCreateTime;
        this.dataUpdateTime = dataUpdateTime;
        this.dataCreateUser = dataCreateUser;
        this.dataUpdateUser = dataUpdateUser;
        this.dataVersion = dataVersion;
    }

    public LnaBalance() {
        super();
    }

    public String getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo == null ? null : loanNo.trim();
    }

    public String getBalAttributes() {
        return balAttributes;
    }

    public void setBalAttributes(String balAttributes) {
        this.balAttributes = balAttributes == null ? null : balAttributes.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public BigDecimal getLastBalance() {
        return lastBalance;
    }

    public void setLastBalance(BigDecimal lastBalance) {
        this.lastBalance = lastBalance;
    }

    public String getBalCode() {
        return balCode;
    }

    public void setBalCode(String balCode) {
        this.balCode = balCode == null ? null : balCode.trim();
    }

    public String getBalAttributesGl() {
        return balAttributesGl;
    }

    public void setBalAttributesGl(String balAttributesGl) {
        this.balAttributesGl = balAttributesGl == null ? null : balAttributesGl.trim();
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
        sb.append(", balAttributes=").append(balAttributes);
        sb.append(", orgId=").append(orgId);
        sb.append(", balanceAmount=").append(balanceAmount);
        sb.append(", lastBalance=").append(lastBalance);
        sb.append(", balCode=").append(balCode);
        sb.append(", balAttributesGl=").append(balAttributesGl);
        sb.append(", dataCreateTime=").append(dataCreateTime);
        sb.append(", dataUpdateTime=").append(dataUpdateTime);
        sb.append(", dataCreateUser=").append(dataCreateUser);
        sb.append(", dataUpdateUser=").append(dataUpdateUser);
        sb.append(", dataVersion=").append(dataVersion);
        sb.append("]");
        return sb.toString();
    }
}