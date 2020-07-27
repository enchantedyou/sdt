package com.ssy.api.entity.enums;

import com.ssy.api.meta.enums.DefaultEnum;

/**
 * @Description 交易类型
 * @Author sunshaoyu
 * @Date 2020/7/22-17:20
 */
public enum E_TRANKIND implements DefaultEnum<String> {

    F("FUND", "F", "fund transaction"),
    P("PARAMETER", "P", "parameter transaction"),
    Q("QUERY", "Q", "query transaction"),
    M("MAINTENANCE", "M", "maintenance transaction");

    private String id;
    private String value;
    private String longname;

    private E_TRANKIND(String id, String value, String longname) {
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
