package com.ssy.api.entity.sump.form;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description events
 * @Author sunshaoyu
 * @Date 2020年06月22日-17:11
 */
public class PTEevents {
    @JSONField(ordinal = 0)
    private PTEdoRequest doRequest;
    @JSONField(ordinal = 5)
    private Map<String, PTEcontrols> condition;
    @JSONField(ordinal = 10)
    private List<String> emit;

    public PTEdoRequest getDoRequest() {
        return doRequest;
    }

    public void setDoRequest(PTEdoRequest doRequest) {
        this.doRequest = doRequest;
    }

    public Map<String, PTEcontrols> getCondition() {
        return condition;
    }

    public void setCondition(LinkedHashMap<String, PTEcontrols> condition) {
        this.condition = condition;
    }

    public List<String> getEmit() {
        return emit;
    }

    public void setEmit(List<String> emit) {
        this.emit = emit;
    }
}
