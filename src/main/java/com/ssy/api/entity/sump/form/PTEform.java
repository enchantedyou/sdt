package com.ssy.api.entity.sump.form;

import com.alibaba.fastjson.annotation.JSONField;
import com.ssy.api.entity.sump.component.PTEbtns;

import java.util.List;
import java.util.Map;

/**
 * @Description form
 * @Author sunshaoyu
 * @Date 2020年06月22日-20:31
 */
public class PTEform {
    @JSONField(ordinal = 0)
    private List<PTEbtns> btns;
    @JSONField(ordinal = 5)
    private String scope;

    @JSONField(ordinal = 10)
    private String initRequest;
    @JSONField(ordinal = 15)
    private List<PTEcontrolsGroup> controlsGroup;

    private List<Map<String, PTEcontrol>> controls;
    private Boolean bringBack;
    private PTEdoRequest doRequest;

    public List<PTEbtns> getBtns() {
        return btns;
    }

    public void setBtns(List<PTEbtns> btns) {
        this.btns = btns;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getInitRequest() {
        return initRequest;
    }

    public void setInitRequest(String initRequest) {
        this.initRequest = initRequest;
    }

    public List<PTEcontrolsGroup> getControlsGroup() {
        return controlsGroup;
    }

    public void setControlsGroup(List<PTEcontrolsGroup> controlsGroup) {
        this.controlsGroup = controlsGroup;
    }

    public List<Map<String, PTEcontrol>> getControls() {
        return controls;
    }

    public void setControls(List<Map<String, PTEcontrol>> controls) {
        this.controls = controls;
    }

    public Boolean getBringBack() {
        return bringBack;
    }

    public void setBringBack(Boolean bringBack) {
        this.bringBack = bringBack;
    }

    public PTEdoRequest getDoRequest() {
        return doRequest;
    }

    public void setDoRequest(PTEdoRequest doRequest) {
        this.doRequest = doRequest;
    }
}
