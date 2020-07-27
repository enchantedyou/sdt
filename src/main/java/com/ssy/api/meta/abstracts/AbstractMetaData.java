package com.ssy.api.meta.abstracts;

import java.io.Serializable;

/**
 * @Description 元数据
 * @Author sunshaoyu
 * @Date 2020年05月26日-17:06
 */
public abstract class AbstractMetaData implements Serializable {

    /**
     * 元数据的位置,例如:LnBaseDict,LnSysEnumType
     */
    protected String location;

    /**
     * 元数据所在文件的后缀
     */
    protected String[] suffix;

    /**
     * 元数据的id,是元数据的唯一标识
     */
    protected String id;

    /**
     * 英文描述
     */
    protected String longName;

    public AbstractMetaData(String location, String id, String longName, String... suffix) {
        this.location = location;
        this.suffix = suffix;
        this.id = id;
        this.longName = longName;
    }

    public AbstractMetaData() {

    }

    public String getLocation() {
        return location;
    }

    public String[] getSuffix() {
        return suffix;
    }

    public String getId() {
        return id;
    }

    public String getLongName() {
        return longName;
    }
}
