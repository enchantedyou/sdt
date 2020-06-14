package com.ssy.api.meta.defaults;

import com.ssy.api.entity.enums.E_RESTRICTION;
import com.ssy.api.meta.abstracts.AbstractRestrictionType;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @Description 默认基础类型
 * @Author sunshaoyu
 * @Date 2020年05月27日-15:49
 */
public class DefaultBaseType extends AbstractRestrictionType implements Serializable {

    private final static String defaultSuffix = ".u_schema.xml";
    private final static E_RESTRICTION defaultRestriction = E_RESTRICTION.BASETYPE;

    public DefaultBaseType(String location, String id, String longName, String base, int maxLength, int fractionDigits) {
        super(location, defaultSuffix, id, longName, base, defaultRestriction, maxLength, fractionDigits ,null);
    }

    @Override
    public String toString() {
        return "DefaultBaseType{" +
                "location='" + location + '\'' +
                ", suffix=" + Arrays.toString(suffix) +
                ", id='" + id + '\'' +
                ", longName='" + longName + '\'' +
                '}';
    }
}
