package com.ssy.api.meta.flowtran;

import com.ssy.api.meta.abstracts.AbstractMetaData;
import com.ssy.api.meta.defaults.Element;

import java.util.List;

/**
 * @Description flowtran的fields
 * @Author sunshaoyu
 * @Date 2020年07月22日-17:33
 */
public class IntfFields extends AbstractMetaData {

    private String scope;
    private boolean required;
    private boolean multi;
    private List<Element> subFieldList;

    public IntfFields(String location, String id, String longName){
        super(location, id, longName, new String[]{});
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isMulti() {
        return multi;
    }

    public void setMulti(boolean multi) {
        this.multi = multi;
    }

    public List<Element> getSubFieldList() {
        return subFieldList;
    }

    public void setSubFieldList(List<Element> subFieldList) {
        this.subFieldList = subFieldList;
    }
}
