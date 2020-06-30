package com.ssy.api.entity.sump.form;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;
import java.util.Map;

/**
 * @Description controlsGroup
 * @Author sunshaoyu
 * @Date 2020年06月22日-20:15
 */
public class PTEcontrolsGroup {
    @JSONField(ordinal = 0)
    private Boolean accordion;
    @JSONField(ordinal = 5)
    private Boolean expand;
    @JSONField(ordinal = 10)
    private List<Map<String, PTEcontrol>> controls;

    public Boolean getAccordion() {
        return accordion;
    }

    public void setAccordion(Boolean accordion) {
        this.accordion = accordion;
    }

    public Boolean getExpand() {
        return expand;
    }

    public void setExpand(Boolean expand) {
        this.expand = expand;
    }

    public List<Map<String, PTEcontrol>> getControls() {
        return controls;
    }

    public void setControls(List<Map<String, PTEcontrol>> controls) {
        this.controls = controls;
    }
}
