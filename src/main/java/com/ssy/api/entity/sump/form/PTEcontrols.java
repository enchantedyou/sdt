package com.ssy.api.entity.sump.form;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 域后事件controls
 * @Author sunshaoyu
 * @Date 2020年06月22日-17:14
 */
public class PTEcontrols {

    @JSONField(ordinal = 0)
    private Map<String, PTEcontrol> control;
    @JSONField(ordinal = 5)
    private List<PTEeventTabs> tabs;

    public Map<String, PTEcontrol> getControl() {
        return control;
    }

    public void setControl(LinkedHashMap<String, PTEcontrol> control) {
        this.control = control;
    }

    public List<PTEeventTabs> getTabs() {
        return tabs;
    }

    public void setTabs(List<PTEeventTabs> tabs) {
        this.tabs = tabs;
    }
}
