package com.ssy.api.entity.sump.form;

import com.alibaba.fastjson.annotation.JSONField;
import com.ssy.api.entity.enums.E_CONTROL;
import com.ssy.api.entity.lang.Params;

import java.util.List;

/**
 * @Description control
 * @Author sunshaoyu
 * @Date 2020年06月22日-17:34
 */
public class PTEcontrol {
    @JSONField(ordinal = 0)
    private String label;
    @JSONField(ordinal = 5)
    private Boolean disabled;

    @JSONField(ordinal = 10)
    private String compute;
    @JSONField(ordinal = 15)
    private List<PTErule> rules;

    @JSONField(ordinal = 20)
    private E_CONTROL control;
    @JSONField(ordinal = 25)
    private Boolean edit;

    @JSONField(ordinal = 30)
    private Params keyField;
    @JSONField(ordinal = 35)
    private PTEtoRequest toRequest;

    @JSONField(ordinal = 40)
    private Integer span;
    @JSONField(ordinal = 45)
    private String placeholder;

    @JSONField(ordinal = 50)
    private String width;
    @JSONField(ordinal = 55)
    private PTEevents events;

    @JSONField(ordinal = 60)
    private String prefix;
    @JSONField(ordinal = 65)
    private PTEcycleSuffix suffix;

    @JSONField(ordinal = 70)
    private Integer rows;
    @JSONField(ordinal = 75)
    private String format;

    @JSONField(ordinal = 80)
    private PTEdict dict;
    @JSONField(ordinal = 85)
    private String valueField;

    @JSONField(ordinal = 90)
    private String labelField;
    @JSONField(ordinal = 95)
    private List<PTEoptions> options;

    @JSONField(ordinal = 100)
    private String startPlaceholder;
    @JSONField(ordinal = 105)
    private String endPlaceholder;

    @JSONField(ordinal = 110)
    private String startField;
    @JSONField(ordinal = 115)
    private String endField;

    @JSONField(ordinal = 120)
    private Boolean unlinkPanels;
    @JSONField(ordinal = 125)
    private Boolean readonly;

    @JSONField(ordinal = 130)
    private Long min;
    @JSONField(ordinal = 135)
    private Integer decimal;

    @JSONField(ordinal = 140)
    private Character thousand;
    @JSONField(ordinal = 145)
    private Long max;

    @JSONField(ordinal = 150)
    private Long step;
    /*@JSONField(ordinal = 150, name = "position")
    private E_POSITION positions;*/

    @JSONField(ordinal = 155)
    private Character symbol;
    @JSONField(ordinal = 160)
    private Integer limit;

    @JSONField(ordinal = 165)
    private Long limitSize;
    @JSONField(ordinal = 170)
    private String type;

    @JSONField(ordinal = 175)
    private Boolean isDrag;
    @JSONField(ordinal = 180)
    private String btnLable;

    @JSONField(ordinal = 185)
    private String value;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public List<PTErule> getRules() {
        return rules;
    }

    public void setRules(List<PTErule> rules) {
        this.rules = rules;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public E_CONTROL getControl() {
        return control;
    }

    public void setControl(E_CONTROL control) {
        this.control = control;
    }

    public Params getKeyField() {
        return keyField;
    }

    public void setKeyField(Params keyField) {
        this.keyField = keyField;
    }

    public PTEtoRequest getToRequest() {
        return toRequest;
    }

    public void setToRequest(PTEtoRequest toRequest) {
        this.toRequest = toRequest;
    }

    public PTEevents getEvents() {
        return events;
    }

    public void setEvents(PTEevents events) {
        this.events = events;
    }

    public Integer getSpan() {
        return span;
    }

    public String getCompute() {
        return compute;
    }

    public void setCompute(String compute) {
        this.compute = compute;
    }

    public void setSpan(Integer span) {
        this.span = span;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public PTEcycleSuffix getSuffix() {
        return suffix;
    }

    public void setSuffix(PTEcycleSuffix suffix) {
        this.suffix = suffix;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public PTEdict getDict() {
        return dict;
    }

    public void setDict(PTEdict dict) {
        this.dict = dict;
    }

    public String getValueField() {
        return valueField;
    }

    public void setValueField(String valueField) {
        this.valueField = valueField;
    }

    public String getLabelField() {
        return labelField;
    }

    public void setLabelField(String labelField) {
        this.labelField = labelField;
    }

    public List<PTEoptions> getOptions() {
        return options;
    }

    public void setOptions(List<PTEoptions> options) {
        this.options = options;
    }

    public String getStartPlaceholder() {
        return startPlaceholder;
    }

    public void setStartPlaceholder(String startPlaceholder) {
        this.startPlaceholder = startPlaceholder;
    }

    public String getEndPlaceholder() {
        return endPlaceholder;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setEndPlaceholder(String endPlaceholder) {
        this.endPlaceholder = endPlaceholder;
    }

    public String getStartField() {
        return startField;
    }

    public void setStartField(String startField) {
        this.startField = startField;
    }

    public String getEndField() {
        return endField;
    }

    public void setEndField(String endField) {
        this.endField = endField;
    }

    public Boolean getUnlinkPanels() {
        return unlinkPanels;
    }

    public void setUnlinkPanels(Boolean unlinkPanels) {
        this.unlinkPanels = unlinkPanels;
    }

    public Boolean getReadonly() {
        return readonly;
    }

    public void setReadonly(Boolean readonly) {
        this.readonly = readonly;
    }

    public Long getMin() {
        return min;
    }

    public void setMin(Long min) {
        this.min = min;
    }

    public Integer getDecimal() {
        return decimal;
    }

    public void setDecimal(Integer decimal) {
        this.decimal = decimal;
    }

    public Character getThousand() {
        return thousand;
    }

    public void setThousand(Character thousand) {
        this.thousand = thousand;
    }

    public Long getMax() {
        return max;
    }

    public void setMax(Long max) {
        this.max = max;
    }

    public Long getStep() {
        return step;
    }

    public void setStep(Long step) {
        this.step = step;
    }

    public Character getSymbol() {
        return symbol;
    }

    public void setSymbol(Character symbol) {
        this.symbol = symbol;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Long getLimitSize() {
        return limitSize;
    }

    public void setLimitSize(Long limitSize) {
        this.limitSize = limitSize;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getDrag() {
        return isDrag;
    }

    public void setDrag(Boolean drag) {
        isDrag = drag;
    }

    public String getBtnLable() {
        return btnLable;
    }

    public void setBtnLable(String btnLable) {
        this.btnLable = btnLable;
    }
}
