package com.ssy.api.entity.enums;

import com.ssy.api.meta.enums.DefaultEnum;

/**
 * @Description 借贷标志枚举
 * @Author sunshaoyu
 * @Date 2020/9/24-16:32
 */
public enum E_DEBITCREDIT implements DefaultEnum<String> {

    D("DEBIT", "D", "debit"),
    C("CREDIT", "C", "credit");

    private String id;
    private String value;
    private String longname;

    private E_DEBITCREDIT(String id, String value, String longname) {
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
