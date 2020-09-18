package com.ssy.api.entity.type.local;

import com.ssy.api.meta.defaults.Element;

import java.util.Map;

/**
 * @Description 字典赋值语句构建输入复合类型
 * @Author sunshaoyu
 * @Date 2020年09月07日-10:15
 */
public class SdFieldSetIn {

    private String sourceEntityId;
    private String sourceVarName;
    private String targetEntityId;
    private String targetVarName;

    Map<String, Element> sourceElementMap;
    Map<String, Element> targetElementMap;

    public SdFieldSetIn(String sourceEntityId, String sourceVarName, String targetEntityId, String targetVarName) {
        this.sourceEntityId = sourceEntityId;
        this.sourceVarName = sourceVarName;
        this.targetEntityId = targetEntityId;
        this.targetVarName = targetVarName;
    }

    public String getSourceEntityId() {
        return sourceEntityId;
    }

    public void setSourceEntityId(String sourceEntityId) {
        this.sourceEntityId = sourceEntityId;
    }

    public String getSourceVarName() {
        return sourceVarName;
    }

    public void setSourceVarName(String sourceVarName) {
        this.sourceVarName = sourceVarName;
    }

    public String getTargetEntityId() {
        return targetEntityId;
    }

    public void setTargetEntityId(String targetEntityId) {
        this.targetEntityId = targetEntityId;
    }

    public String getTargetVarName() {
        return targetVarName;
    }

    public void setTargetVarName(String targetVarName) {
        this.targetVarName = targetVarName;
    }

    public Map<String, Element> getSourceElementMap() {
        return sourceElementMap;
    }

    public void setSourceElementMap(Map<String, Element> sourceElementMap) {
        this.sourceElementMap = sourceElementMap;
    }

    public Map<String, Element> getTargetElementMap() {
        return targetElementMap;
    }

    public void setTargetElementMap(Map<String, Element> targetElementMap) {
        this.targetElementMap = targetElementMap;
    }

    @Override
    public String toString() {
        return "SdFieldSetIn{" +
                "sourceEntityId='" + sourceEntityId + '\'' +
                ", sourceVarName='" + sourceVarName + '\'' +
                ", targetEntityId='" + targetEntityId + '\'' +
                ", targetVarName='" + targetVarName + '\'' +
                '}';
    }
}
