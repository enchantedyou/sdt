package com.ssy.api.entity.sump.form;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @Description 周期后缀
 * @Author sunshaoyu
 * @Date 2020年06月23日-17:06
 */
public class PTEcycleSuffix {
    @JSONField(ordinal = 0)
    private String title;
    @JSONField(ordinal = 5)
    private Integer width;
    @JSONField(ordinal = 10)
    private List<PTEoptions> options;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public List<PTEoptions> getOptions() {
        return options;
    }

    public void setOptions(List<PTEoptions> options) {
        this.options = options;
    }
}
