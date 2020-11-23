package com.ssy.api.entity.table.ct;

public class SmpSysDictLanguage {
    private String dictId;

    private String dictType;

    private String languageType;

    private String dictName;

    public SmpSysDictLanguage(String dictId, String dictType, String languageType, String dictName) {
        this.dictId = dictId;
        this.dictType = dictType;
        this.languageType = languageType;
        this.dictName = dictName;
    }

    public SmpSysDictLanguage() {
        super();
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId == null ? null : dictId.trim();
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType == null ? null : dictType.trim();
    }

    public String getLanguageType() {
        return languageType;
    }

    public void setLanguageType(String languageType) {
        this.languageType = languageType == null ? null : languageType.trim();
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName == null ? null : dictName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", dictId=").append(dictId);
        sb.append(", dictType=").append(dictType);
        sb.append(", languageType=").append(languageType);
        sb.append(", dictName=").append(dictName);
        sb.append("]");
        return sb.toString();
    }
}