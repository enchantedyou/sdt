package com.ssy.api.entity.sump.component;

import com.alibaba.fastjson.annotation.JSONField;
import com.ssy.api.entity.enums.E_BTNTYPE;
import com.ssy.api.entity.lang.Params;
import com.ssy.api.entity.sump.form.PTEdoRequest;
import com.ssy.api.entity.sump.form.PTEtoRequest;

/**
 * @Description btns
 * @Author sunshaoyu
 * @Date 2020年06月22日-20:19
 */
public class PTEbtns {
    @JSONField(ordinal = 0)
    private E_BTNTYPE type;
    @JSONField(ordinal = 5)
    private String label;

    @JSONField(ordinal = 10)
    private PTEtoRequest toRequest;
    @JSONField(ordinal = 15)
    private Params props;

    @JSONField(ordinal = 20)
    private String icon;
    @JSONField(ordinal = 25)
    private PTEdoRequest doRequest;

    public E_BTNTYPE getType() {
        return type;
    }

    public void setType(E_BTNTYPE type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public PTEtoRequest getToRequest() {
        return toRequest;
    }

    public void setToRequest(PTEtoRequest toRequest) {
        this.toRequest = toRequest;
    }

    public Params getProps() {
        return props;
    }

    public void setProps(Params props) {
        this.props = props;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public PTEdoRequest getDoRequest() {
        return doRequest;
    }

    public void setDoRequest(PTEdoRequest doRequest) {
        this.doRequest = doRequest;
    }
}
