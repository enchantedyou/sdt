package com.ssy.api.meta.abstracts;

import com.ssy.api.entity.enums.E_BASE;
import com.ssy.api.entity.enums.E_RESTRICTION;
import com.ssy.api.meta.defaults.DefaultEnumerationType;
import com.ssy.api.meta.defaults.Element;

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

    private Map<String, Element> elementMap;
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

    public AbstractRestrictionType(Map<String, Element> elementMap ,String location, String id, String longName, String dictSuffix, String complexSuffix) {
        super(location, id, longName, dictSuffix, complexSuffix);
        this.elementMap = elementMap;
        restriction = E_RESTRICTION.COMPLEXTYPE;
    }

    public String getFullId(){
        return new StringBuffer(location).append(".").append(id).toString();
    }

    public E_BASE getBase() {
        return base;
    }

    public void setBase(E_BASE base) {
        this.base = base;
    }

    public E_RESTRICTION getRestriction() {
        return restriction;
    }

    public void setRestriction(E_RESTRICTION restriction) {
        this.restriction = restriction;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getFractionDigits() {
        return fractionDigits;
    }

    public void setFractionDigits(int fractionDigits) {
        this.fractionDigits = fractionDigits;
    }

    public Map<String, DefaultEnumerationType> getEnumerationMap() {
        return enumerationMap;
    }

    public void setEnumerationMap(Map<String, DefaultEnumerationType> enumerationMap) {
        this.enumerationMap = enumerationMap;
    }

    @Override
    public String toString() {
        return getFullId();
    }
}
