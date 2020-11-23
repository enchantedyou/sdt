package com.ssy.api.meta.rdp;

/**
 * @Description rdp字典
 * @Author sunshaoyu
 * @Date 2020年11月02日-13:46
 */
public class RdpDict {

    private final String id;
    private final String name;
    private final String type;
    private final String listpath;

    private final String base;
    private final String length;
    private final String dotnum;
    private final String inputtype;

    public static class Builder{
        private final String id;
        private final String name;
        private final String type = "S";
        private String listpath;

        private final String base;
        private final String length = "";
        private final String dotnum = "";
        private final String inputtype = "O";

        public Builder(String id, String name, String base){
            this.id = id;
            this.name = name;
            this.base = base;
        }

        public Builder dictWithList(String listpath){
            this.listpath = listpath;
            return this;
        }

        public RdpDict build(){
            return new RdpDict(this);
        }
    }

    public RdpDict(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.type = builder.type;
        this.listpath = builder.listpath;

        this.base = builder.base;
        this.length = builder.length;
        this.dotnum = builder.dotnum;
        this.inputtype = builder.inputtype;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getListpath() {
        return listpath;
    }

    public String getBase() {
        return base;
    }

    public String getLength() {
        return length;
    }

    public String getDotnum() {
        return dotnum;
    }

    public String getInputtype() {
        return inputtype;
    }

    @Override
    public String toString() {
        return "RdpDict{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", listpath='" + listpath + '\'' +
                ", base='" + base + '\'' +
                ", length='" + length + '\'' +
                ", dotnum='" + dotnum + '\'' +
                ", inputtype='" + inputtype + '\'' +
                '}';
    }
}
