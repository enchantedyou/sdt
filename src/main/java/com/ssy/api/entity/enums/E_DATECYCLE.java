package com.ssy.api.entity.enums;

import com.ssy.api.meta.enums.DefaultEnum;

/**
 * @Description 日期周期
 * @Author sunshaoyu
 * @Date 2020/5/27-13:10
 */
public enum E_DATECYCLE implements DefaultEnum<Integer> {

    DAY("DAY", 5, "day"),
    WEEK("WEEK", 4, "week"),
    MONTH("MONTH", 2, "month"),
    YEAR("YEAR", 1, "year");

    private String id;
    private int value;
    private String longname;

    private E_DATECYCLE(String id, int value, String longname) {
        this.id = id;
        this.value = value;
        this.longname = longname;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String getLongName() {
        return this.longname;
    }
}
