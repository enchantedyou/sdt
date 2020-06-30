package com.ssy.api.entity.enums;

import com.ssy.api.meta.enums.DefaultEnum;

/**
 * @Description 元素位置
 * @Author sunshaoyu
 * @Date 2020/6/22-18:46
 */
public enum E_POSITION implements DefaultEnum<String> {

    center("CENTER", "center", "center"),
    left("POST", "left", "left"),
    right("RIGHT", "right", "right"),
    top("TOP", "top", "top");

    private String id;
    private String value;
    private String longname;

    private E_POSITION(String id, String value, String longname) {
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
