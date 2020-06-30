package com.ssy.api.entity.enums;

import com.ssy.api.meta.enums.DefaultEnum;

/**
 * @Description 请求方式
 * @Author sunshaoyu
 * @Date 2020/6/22-17:04
 */
public enum E_REQUESTMETHOD implements DefaultEnum<String> {

    GET("GET", "GET", "get request"),
    POST("POST", "POST", "post request");

    private String id;
    private String value;
    private String longname;

    private E_REQUESTMETHOD(String id, String value, String longname) {
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
