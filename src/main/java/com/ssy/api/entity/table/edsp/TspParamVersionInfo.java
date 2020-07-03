package com.ssy.api.entity.table.edsp;

import java.util.Date;

public class TspParamVersionInfo {
    private String systemCode;

    private String corporateCode;

    private String paramCode;

    private Integer paramVersion;

    private Date dataCreateTime;

    private Date dataUpdateTime;

    private Date tmtamp;

    public TspParamVersionInfo(String systemCode, String corporateCode, String paramCode, Integer paramVersion, Date dataCreateTime, Date dataUpdateTime, Date tmtamp) {
        this.systemCode = systemCode;
        this.corporateCode = corporateCode;
        this.paramCode = paramCode;
        this.paramVersion = paramVersion;
        this.dataCreateTime = dataCreateTime;
        this.dataUpdateTime = dataUpdateTime;
        this.tmtamp = tmtamp;
    }

    public TspParamVersionInfo() {
        super();
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode == null ? null : systemCode.trim();
    }

    public String getCorporateCode() {
        return corporateCode;
    }

    public void setCorporateCode(String corporateCode) {
        this.corporateCode = corporateCode == null ? null : corporateCode.trim();
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode == null ? null : paramCode.trim();
    }

    public Integer getParamVersion() {
        return paramVersion;
    }

    public void setParamVersion(Integer paramVersion) {
        this.paramVersion = paramVersion;
    }

    public Date getDataCreateTime() {
        return dataCreateTime;
    }

    public void setDataCreateTime(Date dataCreateTime) {
        this.dataCreateTime = dataCreateTime;
    }

    public Date getDataUpdateTime() {
        return dataUpdateTime;
    }

    public void setDataUpdateTime(Date dataUpdateTime) {
        this.dataUpdateTime = dataUpdateTime;
    }

    public Date getTmtamp() {
        return tmtamp;
    }

    public void setTmtamp(Date tmtamp) {
        this.tmtamp = tmtamp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", systemCode=").append(systemCode);
        sb.append(", corporateCode=").append(corporateCode);
        sb.append(", paramCode=").append(paramCode);
        sb.append(", paramVersion=").append(paramVersion);
        sb.append(", dataCreateTime=").append(dataCreateTime);
        sb.append(", dataUpdateTime=").append(dataUpdateTime);
        sb.append(", tmtamp=").append(tmtamp);
        sb.append("]");
        return sb.toString();
    }
}