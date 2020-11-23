package com.ssy.api.meta.rdp;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description rdp下拉列表
 * @Author sunshaoyu
 * @Date 2020年10月29日-17:39
 */
public class RdpListType {

    private final String id;
    private final String type;
    private final String name;
    private final String base;

    private final String nullflag;
    private final String valuedisplayflag;
    private final String sql;
    private final String displayfield;
    private final List<Item> itemList = new ArrayList<>();

    public static class Builder{
        private final String id;
        private final String type;
        private final String name;
        private final String base;

        private String nullflag = "1";
        private String valuedisplayflag = "1";
        private String sql;
        private String displayfield;

        public Builder(String id, String type, String name, String base){
            this.id = id;
            this.type = type;
            this.name = name;
            this.base = base;
        }

        public Builder specialInfo(String nullflag, String valuedisplayflag, String sql, String displayfield){
            this.nullflag = nullflag;
            this.valuedisplayflag = valuedisplayflag;
            this.sql = sql;
            this.displayfield = displayfield;
            return this;
        }

        public RdpListType build(){
            return new RdpListType(this);
        }
    }

    public static class Item{
        private final String id;
        private final String value;
        private final String desc;
        private final String display;

        private Item(String id, String value, String desc, String display){
            this.id = id;
            this.value = value;
            this.desc = desc;
            this.display = display;
        }

        public String getId() {
            return id;
        }

        public String getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }

        public String getDisplay() {
            return display;
        }
    }

    public void addItem(String id, String value, String desc, String display){
        itemList.add(new Item(id, value, desc, display));
    }

    private RdpListType(Builder builder){
        this.id = builder.id;
        this.type = builder.type;
        this.name = builder.name;
        this.base = builder.base;

        this.nullflag = builder.nullflag;
        this.valuedisplayflag = builder.valuedisplayflag;
        this.sql = builder.sql;
        this.displayfield = builder.displayfield;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getBase() {
        return base;
    }

    public String getNullflag() {
        return nullflag;
    }

    public String getValuedisplayflag() {
        return valuedisplayflag;
    }

    public String getSql() {
        return sql;
    }

    public String getDisplayfield() {
        return displayfield;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    @Override
    public String toString() {
        return "RdpListType{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", base='" + base + '\'' +
                ", nullflag='" + nullflag + '\'' +
                ", valuedisplayflag='" + valuedisplayflag + '\'' +
                ", sql='" + sql + '\'' +
                ", displayfield='" + displayfield + '\'' +
                '}';
    }
}
