package com.ssy.api.entity.sump.component;

import com.alibaba.fastjson.annotation.JSONField;
import com.ssy.api.entity.enums.E_POSITION;
import com.ssy.api.entity.sump.form.PTEdoRequest;

import java.util.List;

/**
 * @Description 选项卡
 * @Author sunshaoyu
 * @Date 2020年06月30日-09:35
 */
public class PTEtabs {

    @JSONField(ordinal = 0)
    private String scope;
    @JSONField(ordinal = 5)
    private Boolean initDataOnce;

    @JSONField(ordinal = 10)
    private E_POSITION submitBtnPlace;
    @JSONField(ordinal = 11)
    private List<PTEbtns> btns;
    @JSONField(ordinal = 15)
    private PTEdoRequest doRequest;

    @JSONField(ordinal = 20)
    private List<PTEtabHeader> tabHeader;
    @JSONField(ordinal = 25)
    private String defaultActiveKey;

    @JSONField(ordinal = 30)
    private E_POSITION position;
    @JSONField(ordinal = 35)
    private String styleClass;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Boolean getInitDataOnce() {
        return initDataOnce;
    }

    public void setInitDataOnce(Boolean initDataOnce) {
        this.initDataOnce = initDataOnce;
    }

    public E_POSITION getSubmitBtnPlace() {
        return submitBtnPlace;
    }

    public void setSubmitBtnPlace(E_POSITION submitBtnPlace) {
        this.submitBtnPlace = submitBtnPlace;
    }

    public PTEdoRequest getDoRequest() {
        return doRequest;
    }

    public void setDoRequest(PTEdoRequest doRequest) {
        this.doRequest = doRequest;
    }

    public List<PTEtabHeader> getTabHeader() {
        return tabHeader;
    }

    public void setTabHeader(List<PTEtabHeader> tabHeader) {
        this.tabHeader = tabHeader;
    }

    public String getDefaultActiveKey() {
        return defaultActiveKey;
    }

    public void setDefaultActiveKey(String defaultActiveKey) {
        this.defaultActiveKey = defaultActiveKey;
    }

    public E_POSITION getPosition() {
        return position;
    }

    public void setPosition(E_POSITION position) {
        this.position = position;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public List<PTEbtns> getBtns() {
        return btns;
    }

    public void setBtns(List<PTEbtns> btns) {
        this.btns = btns;
    }
}
