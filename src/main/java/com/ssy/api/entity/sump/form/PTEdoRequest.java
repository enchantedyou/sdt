package com.ssy.api.entity.sump.form;

import com.alibaba.fastjson.annotation.JSONField;
import com.ssy.api.entity.enums.E_REQUESTMETHOD;
import com.ssy.api.entity.lang.Params;

/**
 * @Description doRequest
 * @Author sunshaoyu
 * @Date 2020年06月22日-17:02
 */
public class PTEdoRequest {

    @JSONField(ordinal = 0)
    private String url;
    @JSONField(ordinal = 5)
    private E_REQUESTMETHOD method;

    @JSONField(ordinal = 10)
    private Params params;
    @JSONField(ordinal = 15)
    private Params resultSet;
    @JSONField(ordinal = 20)
    private String servicecode;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public E_REQUESTMETHOD getMethod() {
        return method;
    }

    public void setMethod(E_REQUESTMETHOD method) {
        this.method = method;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public Params getResultSet() {
        return resultSet;
    }

    public void setResultSet(Params resultSet) {
        this.resultSet = resultSet;
    }

    public String getServicecode() {
        return servicecode;
    }

    public void setServicecode(String servicecode) {
        this.servicecode = servicecode;
    }
}
