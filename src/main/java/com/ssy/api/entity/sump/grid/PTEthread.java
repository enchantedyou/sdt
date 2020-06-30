package com.ssy.api.entity.sump.grid;

import com.alibaba.fastjson.annotation.JSONField;
import com.ssy.api.entity.enums.E_POSITION;
import com.ssy.api.entity.enums.E_THREADTYPE;
import com.ssy.api.entity.lang.Params;
import com.ssy.api.entity.sump.component.PTEbtns;
import com.ssy.api.entity.sump.form.PTEdict;

import java.util.List;

/**
 * @Description 表格体
 * @Author sunshaoyu
 * @Date 2020年06月28日-09:51
 */
public class PTEthread {

    @JSONField(ordinal = 0)
    private E_THREADTYPE type;
    @JSONField(ordinal = 5)
    private String prop;

    @JSONField(ordinal = 10)
    private String label;
    @JSONField(ordinal = 15)
    private E_POSITION align;

    @JSONField(ordinal = 20)
    private Boolean sortable;
    @JSONField(ordinal = 25)
    private E_POSITION fixed;

    @JSONField(ordinal = 30)
    private String width;
    @JSONField(ordinal = 35)
    private List<PTEbtns> btns;

    @JSONField(ordinal = 40)
    private PTEdict dict;
    @JSONField(ordinal = 45)
    private Params popover;

    @JSONField(ordinal = 50)
    private Boolean showColumn;

    public E_THREADTYPE getType() {
        return type;
    }

    public void setType(E_THREADTYPE type) {
        this.type = type;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public E_POSITION getAlign() {
        return align;
    }

    public void setAlign(E_POSITION align) {
        this.align = align;
    }

    public Boolean getSortable() {
        return sortable;
    }

    public void setSortable(Boolean sortable) {
        this.sortable = sortable;
    }

    public E_POSITION getFixed() {
        return fixed;
    }

    public void setFixed(E_POSITION fixed) {
        this.fixed = fixed;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public List<PTEbtns> getBtns() {
        return btns;
    }

    public void setBtns(List<PTEbtns> btns) {
        this.btns = btns;
    }

    public PTEdict getDict() {
        return dict;
    }

    public void setDict(PTEdict dict) {
        this.dict = dict;
    }

    public Params getPopover() {
        return popover;
    }

    public void setPopover(Params popover) {
        this.popover = popover;
    }

    public Boolean getShowColumn() {
        return showColumn;
    }

    public void setShowColumn(Boolean showColumn) {
        this.showColumn = showColumn;
    }
}