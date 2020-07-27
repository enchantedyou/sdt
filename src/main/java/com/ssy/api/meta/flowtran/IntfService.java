package com.ssy.api.meta.flowtran;

import com.ssy.api.meta.abstracts.AbstractMetaData;

/**
 * @Description 接口服务
 * @Author sunshaoyu
 * @Date 2020年07月22日-17:42
 */
public class IntfService extends AbstractMetaData {

    private boolean mappingToProperty;
    private String serviceName;
    private String test;
    //private List<Element> serviceInput;
    //private List<Element> serviceOutput;

    public IntfService(String location, String id, String longName) {
        super(location, id, longName);
    }

    public boolean isMappingToProperty() {
        return mappingToProperty;
    }

    public void setMappingToProperty(boolean mappingToProperty) {
        this.mappingToProperty = mappingToProperty;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
