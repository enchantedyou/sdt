package com.ssy.api.entity.sump.form;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Description options
 * @Author sunshaoyu
 * @Date 2020年06月22日-18:39
 */
public class PTEoptions {
    @JSONField(ordinal = 0)
    private String label;
    @JSONField(ordinal = 5)
    private String value;
    @JSONField(ordinal = 10)
    private Boolean disabled;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
}
