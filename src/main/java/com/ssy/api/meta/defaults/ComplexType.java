package com.ssy.api.meta.defaults;

import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.meta.abstracts.AbstractRestrictionType;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 默认的复合类型
 * @Author sunshaoyu
 * @Date 2020年05月27日-14:54
 */
public class ComplexType extends AbstractRestrictionType implements Serializable {

    private boolean isDict;
    private boolean isAbstract = false;
    private boolean isIntroduct = false;
    private Map<String, Element> elementMap = new ConcurrentHashMap<>();

    public ComplexType(String location, String id, String longName, boolean isDict, Map<String, Element> elementMap) {
        super(elementMap, location, id, longName, SdtConst.DICT_SUFFIX, SdtConst.COMPLEX_SUFFIX);
        this.isDict = isDict;
        this.elementMap = elementMap;
    }

    /**
     * @Description 获取元数据全名,例如:
     * 枚举:LnSysEnumType.xxx
     * 字典:LnbaseDict.A.xxx
     * @Author sunshaoyu
     * @param elementId
     * @Date 2020/5/26-17:28
     * @return java.lang.String
     */
    public String getFullId(String elementId) {
        if(null == elementMap || elementMap.isEmpty()){
            return null;
        }else{
            if(isDict){
                StringBuffer buffer = new StringBuffer();
                return buffer.append(this.getLocation()).append(".").append(this.getId()).append(".").append(elementId).toString();
            }else{
                Element element = elementMap.get(elementId);
                return null == element ? null : element.getRef();
            }
        }
    }

    public boolean isDict() {
        return this.isDict;
    }

    public boolean isAbstract() {
        return this.isAbstract;
    }

    public boolean isIntroduct() {
        return this.isIntroduct;
    }

    public int putElementMap(Map<String, Element> record) {
        elementMap.putAll(record);
        return elementMap.size();
    }

    public Map<String, Element> getElementMap() {
        return this.elementMap;
    }

    public Element getElementSingle(Serializable key) {
        return (null == elementMap || elementMap.isEmpty()) ? null : elementMap.get(key);
    }

    public String getLocation() {
        return this.location;
    }

    public String[] getSuffix() {
        return this.suffix;
    }

    public String getId() {
        return this.id;
    }

    public String getLongName() {
        return this.longName;
    }

    @Override
    public String toString() {
        return "ComplexType{" +
                "isDict=" + isDict +
                ", isAbstract=" + isAbstract +
                ", isIntroduct=" + isIntroduct +
                ", location='" + location + '\'' +
                ", suffix=" + Arrays.toString(suffix) +
                ", id='" + id + '\'' +
                ", longName='" + longName + '\'' +
                '}';
    }
}
