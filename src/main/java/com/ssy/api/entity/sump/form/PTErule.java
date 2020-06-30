package com.ssy.api.entity.sump.form;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Description rule
 * @Author sunshaoyu
 * @Date 2020年06月22日-17:41
 */
public class PTErule {
    @JSONField(ordinal = 0)
    private Boolean required;
    @JSONField(ordinal = 5)
    private String type;

    @JSONField(ordinal = 10)
    private Integer min;
    @JSONField(ordinal = 15)
    private Integer max;

    @JSONField(ordinal = 20)
    private Integer len;
    @JSONField(ordinal = 25)
    private String pattern;

    @JSONField(ordinal = 30)
    private String expreesion;
    @JSONField(ordinal = 35)
    private String message;

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getLen() {
        return len;
    }

    public void setLen(Integer len) {
        this.len = len;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getExpreesion() {
        return expreesion;
    }

    public void setExpreesion(String expreesion) {
        this.expreesion = expreesion;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
