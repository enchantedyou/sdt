package com.ssy.api.entity.enums;

import com.ssy.api.meta.enums.DefaultEnum;

/**
 * @Description 表类型
 * @Author sunshaoyu
 * @Date 2020/5/27-14:25
 */
public enum E_TABLETYPE implements DefaultEnum<String> {

    ORDINARY("ORDINARY", "ORDINARY", "普通表"),
    BROADCAST("BROADCAST", "BROADCAST", "广播表"),
    SHARDING("SHARDING", "SHARDING", "分片表");

    private String id;
    private String value;
    private String longname;

    private E_TABLETYPE(String id, String value, String longname) {
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
