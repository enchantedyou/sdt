package com.ssy.api.entity.sump.form;

import com.alibaba.fastjson.annotation.JSONField;
import com.ssy.api.entity.enums.E_REQUESTMETHOD;
import com.ssy.api.entity.lang.Params;

/**
 * @Description toRequest
 * @Author sunshaoyu
 * @Date 2020/6/22-18:29
 */
public class PTEtoRequest {

    @JSONField(ordinal = 0)
    private String url;
    @JSONField(ordinal = 5)
    private E_REQUESTMETHOD method;
    @JSONField(ordinal = 10)
    private Params params;

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
}
