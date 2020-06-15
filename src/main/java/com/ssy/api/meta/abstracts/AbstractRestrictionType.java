package com.ssy.api.meta.abstracts;

import com.ssy.api.entity.enums.E_BASE;
import com.ssy.api.entity.enums.E_RESTRICTION;
import com.ssy.api.meta.defaults.DefaultEnumerationType;

import java.io.Serializable;
import java.util.Map;

/**
 * @Description 限制类型
 * @Author sunshaoyu
 * @Date 2020年05月27日-11:46
 */
public abstract class AbstractRestrictionType extends AbstractMetaData implements Serializable {

    private E_BASE base;
    private E_RESTRICTION restriction;

    private int maxLength;
    private int fractionDigits;
    private Map<String, DefaultEnumerationType> enumerationMap;

    public AbstractRestrictionType(String location, String suffix, String id, String longName, String base,
                                   E_RESTRICTION restriction, int maxLength, int fractionDigits, Map<String, DefaultEnumerationType> enumerationMap) {
        super(location, id, longName, suffix);

        try{
            this.base = E_BASE.valueOf(base.toUpperCase());
        }catch(IllegalArgumentException e){
            this.base = E_BASE.STRING;
        }

        this.maxLength = maxLength;
        this.fractionDigits = fractionDigits;
        this.restriction = restriction;
        this.enumerationMap = enumerationMap;
    }

    public E_BASE getBase() {
        return base;
    }

    public E_RESTRICTION getRestriction() {
        return restriction;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public int getFractionDigits() {
        return fractionDigits;
    }

    public String getFullId(){
        return new StringBuffer(location).append(".").append(id).toString();
    }
}
