package com.ssy.api.entity.enums;

import com.ssy.api.meta.enums.DefaultEnum;

/**
 * @Description 状态(成功/失败)
 * @Author sunshaoyu
 * @Date 2020年07月13日-13:11
 */
public enum  E_STATUS implements DefaultEnum<String> {

    S("SUCCESS", "S", "success"),
    F("FAILURE", "F", "failure");

    private String id;
    private String value;
    private String longname;

    private E_STATUS(String id, String value, String longname) {
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
