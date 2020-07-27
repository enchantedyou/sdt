package com.ssy.api.entity.enums;

import com.ssy.api.meta.enums.DefaultEnum;

/**
 * @Description 限制类型枚举
 * @Author sunshaoyu
 * @Date 2020/5/27-13:38
 */
public enum E_RESTRICTION implements DefaultEnum<String> {

    BASETYPE("BASETYPE", "B", "base type"),
    ENUMTYPE("ENUMTYPE", "E", "enum type"),
    COMPLEXTYPE("COMPLEXTYPE", "C", "complex type");

    private String id;
    private String value;
    private String longname;

    private E_RESTRICTION(String id, String value, String longname) {
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
