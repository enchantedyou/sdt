package com.ssy.api.entity.sump.form;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Description tabs
 * @Author sunshaoyu
 * @Date 2020年06月22日-20:08
 */
public class PTEeventTabs {
    @JSONField(ordinal = 0)
    private String id;
    @JSONField(ordinal = 5)
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
