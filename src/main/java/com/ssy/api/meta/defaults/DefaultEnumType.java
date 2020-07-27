package com.ssy.api.meta.defaults;

import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.enums.E_RESTRICTION;
import com.ssy.api.meta.abstracts.AbstractRestrictionType;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

/**
 * @Description
 * @Author sunshaoyu
 * @Date 2020年05月27日-16:21
 */
public class DefaultEnumType extends AbstractRestrictionType implements Serializable {

    private final static E_RESTRICTION defaultRestriction = E_RESTRICTION.ENUMTYPE;
    private final static int defaultFractionDigits = 0;
    private Map<String, DefaultEnumerationType> enumerationMap;

    public DefaultEnumType(String location, String id, String longName, String base, int maxLength, Map<String, DefaultEnumerationType> enumerationMap) {
        super(location, SdtConst.ENUM_SUFFIX, id, longName, base, defaultRestriction, maxLength, defaultFractionDigits, enumerationMap);
        this.enumerationMap = enumerationMap;
    }

    public Map<String, DefaultEnumerationType> getEnumerationMap() {
        return enumerationMap;
    }

    public void setEnumerationMap(Map<String, DefaultEnumerationType> enumerationMap) {
        this.enumerationMap = enumerationMap;
    }

    @Override
    public String toString() {
        return "DefaultEnumType{" +
                "location='" + location + '\'' +
                ", suffix=" + Arrays.toString(suffix) +
                ", id='" + id + '\'' +
                ", longName='" + longName + '\'' +
                ", enumerationMap=" + enumerationMap + '\'' +
                '}';
    }
}
