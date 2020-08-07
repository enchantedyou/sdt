package com.ssy.api.entity.table.ct;

public class SmpSysDict {
    private String dictId;

    private String dictType;

    private String dictName;

    private String parentDictType;

    private String parentDictId;

    private String status;

    private Long sortNo;

    private String timestamp;

    private String dictTypeName;

    public SmpSysDict(String dictId, String dictType, String dictName, String parentDictType, String parentDictId, String status, Long sortNo, String timestamp, String dictTypeName) {
        this.dictId = dictId;
        this.dictType = dictType;
        this.dictName = dictName;
        this.parentDictType = parentDictType;
        this.parentDictId = parentDictId;
        this.status = status;
        this.sortNo = sortNo;
        this.timestamp = timestamp;
        this.dictTypeName = dictTypeName;
    }

    public SmpSysDict() {
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

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName == null ? null : dictName.trim();
    }

    public String getParentDictType() {
        return parentDictType;
    }

    public void setParentDictType(String parentDictType) {
        this.parentDictType = parentDictType == null ? null : parentDictType.trim();
    }

    public String getParentDictId() {
        return parentDictId;
    }

    public void setParentDictId(String parentDictId) {
        this.parentDictId = parentDictId == null ? null : parentDictId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getSortNo() {
        return sortNo;
    }

    public void setSortNo(Long sortNo) {
        this.sortNo = sortNo;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp == null ? null : timestamp.trim();
    }

    public String getDictTypeName() {
        return dictTypeName;
    }

    public void setDictTypeName(String dictTypeName) {
        this.dictTypeName = dictTypeName == null ? null : dictTypeName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", dictId=").append(dictId);
        sb.append(", dictType=").append(dictType);
        sb.append(", dictName=").append(dictName);
        sb.append(", parentDictType=").append(parentDictType);
        sb.append(", parentDictId=").append(parentDictId);
        sb.append(", status=").append(status);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", timestamp=").append(timestamp);
        sb.append(", dictTypeName=").append(dictTypeName);
        sb.append("]");
        return sb.toString();
    }
}