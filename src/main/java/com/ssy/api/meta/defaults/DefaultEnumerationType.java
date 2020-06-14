package com.ssy.api.meta.defaults;

import com.ssy.api.meta.abstracts.AbstractMetaData;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @Description 默认枚举类型
 * @Author sunshaoyu
 * @Date 2020/6/12-23:39
 */
public class DefaultEnumerationType extends AbstractMetaData implements Serializable {

    private final static String defaultSuffix = ".e_schema.xml";
    private String value;

    public DefaultEnumerationType(String location, String id, String longName, String value) {
        super(location, id, longName, defaultSuffix);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DefaultEnumerationType{" +
                "value='" + value + '\'' +
                ", location='" + location + '\'' +
                ", suffix=" + Arrays.toString(suffix) +
                ", id='" + id + '\'' +
                ", longName='" + longName + '\'' +
                '}';
    }
}
