package com.ssy.api.entity.enums;

import com.ssy.api.meta.enums.DefaultEnum;

/**
 * @Description flowtran接口类型
 * @Author sunshaoyu
 * @Date 2020/7/23-10:48
 */
public enum E_TRANINTFTYPE implements DefaultEnum<String> {

    input("INPUT", "input", "input interface"),
    output("OUPUT", "output", "output interface"),
    property("PROPERTY", "property", "property interface"),
    printer("PRINTER", "printer", "printer interface");

    private String id;
    private String value;
    private String longname;

    private E_TRANINTFTYPE(String id, String value, String longname) {
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
