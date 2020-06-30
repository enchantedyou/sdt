package com.ssy.api.entity.sump.form;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @Description dict
 * @Author sunshaoyu
 * @Date 2020年06月22日-18:34
 */
public class PTEdict {
    @JSONField(ordinal = 0)
    private String dictType;
    @JSONField(ordinal = 5)
    private String format;

    @JSONField(ordinal = 10)
    private List<String> dictKey;
    @JSONField(ordinal = 15)
    private String labelField;

    @JSONField(ordinal = 20)
    private String valueField;
    @JSONField(ordinal = 25)
    private PTEdoRequest doRequest;

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public List<String> getDictKey() {
        return dictKey;
    }

    public void setDictKey(List<String> dictKey) {
        this.dictKey = dictKey;
    }

    public String getLabelField() {
        return labelField;
    }

    public void setLabelField(String labelField) {
        this.labelField = labelField;
    }

    public String getValueField() {
        return valueField;
    }

    public void setValueField(String valueField) {
        this.valueField = valueField;
    }

    public PTEdoRequest getDoRequest() {
        return doRequest;
    }

    public void setDoRequest(PTEdoRequest doRequest) {
        this.doRequest = doRequest;
    }
}
