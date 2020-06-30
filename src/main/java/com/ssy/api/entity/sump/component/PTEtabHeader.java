package com.ssy.api.entity.sump.component;

import com.alibaba.fastjson.annotation.JSONField;
import com.ssy.api.entity.lang.Params;
import com.ssy.api.entity.sump.form.PTEtoRequest;

/**
 * @Description 选项卡头
 * @Author sunshaoyu
 * @Date 2020年06月30日-09:36
 */
public class PTEtabHeader {

    @JSONField(ordinal = 0)
    private String id;
    @JSONField(ordinal = 5)
    private String  label;

    @JSONField(ordinal = 10)
    private Boolean closable;
    @JSONField(ordinal = 15)
    private Boolean disabled;

    @JSONField(ordinal = 20)
    private Params params;
    @JSONField(ordinal = 25)
    private String icon;

    @JSONField(ordinal = 30)
    private PTEtoRequest toRequest;
    @JSONField(ordinal = 35)
    private Boolean hide;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getClosable() {
        return closable;
    }

    public void setClosable(Boolean closable) {
        this.closable = closable;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public PTEtoRequest getToRequest() {
        return toRequest;
    }

    public void setToRequest(PTEtoRequest toRequest) {
        this.toRequest = toRequest;
    }

    public Boolean getHide() {
        return hide;
    }

    public void setHide(Boolean hide) {
        this.hide = hide;
    }
}
