package com.ssy.api.entity.enums;

import com.ssy.api.meta.enums.DefaultEnum;

/**
 * @Description 数据操作类型
 * @Author sunshaoyu
 * @Date 2020/8/7-13:37
 */
public enum E_DATAOPERATE implements DefaultEnum<String> {

    A("ADD", "A", "add"),
    M("MODIFY", "M", "modify"),
    D("DELETE", "D", "delete");

    private String id;
    private String value;
    private String longname;

    private E_DATAOPERATE(String id, String value, String longname) {
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
