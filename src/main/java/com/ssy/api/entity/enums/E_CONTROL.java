package com.ssy.api.entity.enums;

import com.ssy.api.meta.enums.DefaultEnum;

/**
 * @Description 表单元素类型控制
 * @Author sunshaoyu
 * @Date 2020/6/22-17:46
 */
public enum E_CONTROL implements DefaultEnum<String> {

    text("TEXT", "text", "text"),
    textarea("TEXTAREA", "textarea", "text area"),
    string("STRING", "string", "string"),
    select("SELECT", "select", "select"),
    remoteSelect("REMOTESELECT", "remoteSelect", "remote select"),
    cascader("CASCADER", "cascader", "cascader"),
    checkbox("CHECKBOX", "checkbox", "check box"),
    radio("RADIO", "radio", "radio"),
    SWITCH("SWITCH", "switch", "switch"),
    dateTimePicker("DATETIMEPICKER", "dateTimePicker", "date time picker"),
    timePicker("TIMEPICKER", "timePicker", "time picker"),
    inputNumber("INPUTNUMBER", "inputNumber", "input number"),
    currency("CURRENCY", "currency", "currency"),
    password("PASSWORD", "password", "password"),
    lookup("LOOKUP", "lookup", "lookup"),
    transfer("TRANSFER", "transfer", "transfer"),
    file("FILE", "file", "file"),
    hidden("HIDDEN", "hidden", "hidden");

    private String id;
    private String value;
    private String longname;

    private E_CONTROL(String id, String value, String longname) {
        this.id = id;
        this.value = value;
        this.longname = longname;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String getLongName() {
        return this.longname;
    }
}
