package com.ssy.api.entity.sump.component;

import com.alibaba.fastjson.annotation.JSONField;
import com.ssy.api.entity.sump.form.PTEform;
import com.ssy.api.entity.sump.grid.PTEdatagrid;

/**
 * @Description layout
 * @Author sunshaoyu
 * @Date 2020年06月22日-20:32
 */
public class PTElayout {
    @JSONField(ordinal = 0)
    private PTEform form;
    @JSONField(ordinal = 5)
    private PTEdatagrid datagrid;

    @JSONField(ordinal = 10)
    private PTEtoolbar toolbar;
    @JSONField(ordinal = 15)
    private PTEtabs tabs;
    @JSONField(ordinal = 20)
    private PTEfooter footer;

    public PTEform getForm() {
        return form;
    }

    public void setForm(PTEform form) {
        this.form = form;
    }

    public PTEdatagrid getDatagrid() {
        return datagrid;
    }

    public void setDatagrid(PTEdatagrid datagrid) {
        this.datagrid = datagrid;
    }

    public PTEtoolbar getToolbar() {
        return toolbar;
    }

    public void setToolbar(PTEtoolbar toolbar) {
        this.toolbar = toolbar;
    }

    public PTEtabs getTabs() {
        return tabs;
    }

    public void setTabs(PTEtabs tabs) {
        this.tabs = tabs;
    }

    public PTEfooter getFooter() {
        return footer;
    }

    public void setFooter(PTEfooter footer) {
        this.footer = footer;
    }
}
