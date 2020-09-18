package com.ssy.api.entity.type.local;

/**
 * @Description 字典搜索输出复合类型
 * @Author sunshaoyu
 * @Date 2020年07月16日-17:10
 */
public class SdSearchDictOut {

    private String id;
    private String ref;
    private String type;
    private Integer length;
    private String longName;
    private String desc;

    public SdSearchDictOut(String id, String ref, String longName, String desc, String type, Integer length) {
        this.id = id;
        this.ref = ref;
        this.type = type;
        this.longName = longName;
        this.desc = desc;
        this.length = length;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "SdSearchDictOut{" +
                "id='" + id + '\'' +
                ", ref='" + ref + '\'' +
                ", type='" + type + '\'' +
                ", length=" + length +
                ", longName='" + longName + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
