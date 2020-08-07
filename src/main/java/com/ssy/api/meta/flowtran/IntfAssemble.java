package com.ssy.api.meta.flowtran;

import com.ssy.api.meta.defaults.Element;

import java.io.Serializable;
import java.util.List;

/**
 * @Description flowtran的接口集(输入、输出、属性)
 * @Author sunshaoyu
 * @Date 2020年07月22日-17:26
 */
public class IntfAssemble implements Serializable {

    private boolean packMode;
    private boolean asParm;
    private List<Element> fieldList;
    private List<IntfFields> fieldsList;

    public IntfAssemble() {
    }

    public boolean isPackMode() {
        return packMode;
    }

    public void setPackMode(boolean packMode) {
        this.packMode = packMode;
    }

    public boolean isAsParm() {
        return asParm;
    }

    public void setAsParm(boolean asParm) {
        this.asParm = asParm;
    }

    public List<Element> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<Element> fieldList) {
        this.fieldList = fieldList;
    }

    public List<IntfFields> getFieldsList() {
        return fieldsList;
    }

    public void setFieldsList(List<IntfFields> fieldsList) {
        this.fieldsList = fieldsList;
    }
}
