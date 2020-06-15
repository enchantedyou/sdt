package com.ssy.api.meta.defaults;

import com.ssy.api.meta.abstracts.AbstractMetaData;
import com.ssy.api.meta.abstracts.AbstractRestrictionType;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @Description 默认子元素
 * @Author sunshaoyu
 * @Date 2020年05月27日-15:10
 */
public class Element extends AbstractMetaData implements Serializable {

    private AbstractRestrictionType type;
    private String desc;
    private String ref;
    private boolean isRequired = false;

    private boolean isMulti = false;
    private boolean isRange = false;
    private boolean isArray = false;
    private boolean isFinal = false;

    private boolean isOverride = false;
    private boolean isAllowSubType = false;

    public Element(String location, String id, String longName, AbstractRestrictionType type, String desc, String ref) {
        super(location, id, longName, new String[]{});
        this.type = type;
        this.desc = desc;
        this.ref = ref;
    }

    public AbstractRestrictionType getType() {
        return this.type;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getRef() {
        return this.ref;
    }

    public boolean isRequired() {
        return this.isRequired;
    }

    public boolean isMulti() {
        return this.isMulti;
    }

    public boolean isRange() {
        return this.isRange;
    }

    public boolean isArray() {
        return this.isArray;
    }

    public boolean isFinal() {
        return this.isFinal;
    }

    public boolean isOverride() {
        return this.isOverride;
    }

    public boolean isAllowSubType() {
        return this.isAllowSubType;
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
        return "Element{" +
                "desc='" + desc + '\'' +
                ", ref='" + ref + '\'' +
                ", isRequired=" + isRequired +
                ", isMulti=" + isMulti +
                ", isRange=" + isRange +
                ", isArray=" + isArray +
                ", isFinal=" + isFinal +
                ", isOverride=" + isOverride +
                ", isAllowSubType=" + isAllowSubType +
                ", location='" + location + '\'' +
                ", suffix=" + Arrays.toString(suffix) +
                ", id='" + id + '\'' +
                ", longName='" + longName + '\'' +
                ", type=" + type + '\'' +
                '}';
    }
}
