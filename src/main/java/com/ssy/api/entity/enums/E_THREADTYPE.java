package com.ssy.api.entity.enums;

import com.ssy.api.meta.enums.DefaultEnum;

/**
 * @Description 表格元素类型
 * @Author sunshaoyu
 * @Date 2020/6/28-19:07
 */
public enum E_THREADTYPE implements DefaultEnum<String> {

    selection("selection", "selection", "selection"),
    radio("radio", "radio", "radio"),
    index("index", "index", "index"),
    expand("expand", "expand", "expand");

    private String id;
    private String value;
    private String longname;

    private E_THREADTYPE(String id, String value, String longname) {
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
