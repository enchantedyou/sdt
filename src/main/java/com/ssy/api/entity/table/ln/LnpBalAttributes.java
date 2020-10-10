package com.ssy.api.entity.table.ln;

public class LnpBalAttributes {
    private String balAttributes;

    private String orgId;

    private String balAttributesName;

    private String doubleEntryInd;

    private String debitCredit;

    private String balCode;

    private String balAttributesGl;

    private String balAttributesGlName;

    private String dataCreateUser;

    private String dataCreateTime;

    private String dataUpdateUser;

    private String dataUpdateTime;

    private Long dataVersion;

    public LnpBalAttributes(String balAttributes, String orgId, String balAttributesName, String doubleEntryInd, String debitCredit, String balCode, String balAttributesGl, String balAttributesGlName, String dataCreateUser, String dataCreateTime, String dataUpdateUser, String dataUpdateTime, Long dataVersion) {
        this.balAttributes = balAttributes;
        this.orgId = orgId;
        this.balAttributesName = balAttributesName;
        this.doubleEntryInd = doubleEntryInd;
        this.debitCredit = debitCredit;
        this.balCode = balCode;
        this.balAttributesGl = balAttributesGl;
        this.balAttributesGlName = balAttributesGlName;
        this.dataCreateUser = dataCreateUser;
        this.dataCreateTime = dataCreateTime;
        this.dataUpdateUser = dataUpdateUser;
        this.dataUpdateTime = dataUpdateTime;
        this.dataVersion = dataVersion;
    }

    public LnpBalAttributes() {
        super();
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

    public String getBalAttributesName() {
        return balAttributesName;
    }

    public void setBalAttributesName(String balAttributesName) {
        this.balAttributesName = balAttributesName == null ? null : balAttributesName.trim();
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

    public String getBalAttributesGlName() {
        return balAttributesGlName;
    }

    public void setBalAttributesGlName(String balAttributesGlName) {
        this.balAttributesGlName = balAttributesGlName == null ? null : balAttributesGlName.trim();
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
        sb.append(", balAttributes=").append(balAttributes);
        sb.append(", orgId=").append(orgId);
        sb.append(", balAttributesName=").append(balAttributesName);
        sb.append(", doubleEntryInd=").append(doubleEntryInd);
        sb.append(", debitCredit=").append(debitCredit);
        sb.append(", balCode=").append(balCode);
        sb.append(", balAttributesGl=").append(balAttributesGl);
        sb.append(", balAttributesGlName=").append(balAttributesGlName);
        sb.append(", dataCreateUser=").append(dataCreateUser);
        sb.append(", dataCreateTime=").append(dataCreateTime);
        sb.append(", dataUpdateUser=").append(dataUpdateUser);
        sb.append(", dataUpdateTime=").append(dataUpdateTime);
        sb.append(", dataVersion=").append(dataVersion);
        sb.append("]");
        return sb.toString();
    }
}