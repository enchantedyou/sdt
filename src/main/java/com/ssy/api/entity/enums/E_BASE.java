package com.ssy.api.entity.enums;

import com.ssy.api.meta.enums.DefaultEnum;

/**
 * @Description 基础类型枚举
 * @Author sunshaoyu
 * @Date 2020/5/27-13:10
 */
public enum E_BASE implements DefaultEnum<String> {

    STRING("STRING", "string", "string"),
    LONG("LONG", "long", "long"),
    DECIMAL("DECIMAL", "decimal", "decimal");

    private String id;
    private String value;
    private String longname;

    private E_BASE(String id, String value, String longname) {
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
