package com.ssy.api.entity.type.local;

import com.ssy.api.entity.enums.E_TRANKIND;

/**
 * @Description 元数据生成输入复合类型
 * @Author sunshaoyu
 * @Date 2020年08月11日-09:39
 */
public class SdMetaGen {

    private String originalFlowtranId;
    private String flowtranId;
    private String longname;
    private E_TRANKIND trxnKind;

    private String serviceLocation;
    private String serviceId;
    private String complexLocation;
    private String complexId;

    private String valName;
    private String inputFields;
    private String outputFields;

    public SdMetaGen(){

    }

    public SdMetaGen(String originalFlowtranId, String flowtranId, String longname, E_TRANKIND trxnKind, String serviceLocation, String serviceId, String complexLocation, String complexId, String valName, String inputFields, String outputFields) {
        this.originalFlowtranId = originalFlowtranId;
        this.flowtranId = flowtranId;
        this.longname = longname;
        this.trxnKind = trxnKind;
        this.serviceLocation = serviceLocation;
        this.serviceId = serviceId;
        this.complexLocation = complexLocation;
        this.complexId = complexId;
        this.valName = valName;
        this.inputFields = inputFields;
        this.outputFields = outputFields;
    }

    public String getOriginalFlowtranId() {
        return originalFlowtranId;
    }

    public void setOriginalFlowtranId(String originalFlowtranId) {
        this.originalFlowtranId = originalFlowtranId;
    }

    public String getFlowtranId() {
        return flowtranId;
    }

    public void setFlowtranId(String flowtranId) {
        this.flowtranId = flowtranId;
    }

    public String getLongname() {
        return longname;
    }

    public void setLongname(String longname) {
        this.longname = longname;
    }

    public E_TRANKIND getTrxnKind() {
        return trxnKind;
    }

    public void setTrxnKind(E_TRANKIND trxnKind) {
        this.trxnKind = trxnKind;
    }

    public String getServiceLocation() {
        return serviceLocation;
    }

    public void setServiceLocation(String serviceLocation) {
        this.serviceLocation = serviceLocation;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getComplexLocation() {
        return complexLocation;
    }

    public void setComplexLocation(String complexLocation) {
        this.complexLocation = complexLocation;
    }

    public String getComplexId() {
        return complexId;
    }

    public void setComplexId(String complexId) {
        this.complexId = complexId;
    }

    public String getInputFields() {
        return inputFields;
    }

    public void setInputFields(String inputFields) {
        this.inputFields = inputFields;
    }

    public String getOutputFields() {
        return outputFields;
    }

    public void setOutputFields(String outputFields) {
        this.outputFields = outputFields;
    }

    public String getValName() {
        return valName;
    }

    public void setValName(String valName) {
        this.valName = valName;
    }
}
