package com.ssy.api.meta.defaults;

import com.ssy.api.entity.constant.SdtConst;
import com.ssy.api.entity.enums.E_TABLETYPE;
import com.ssy.api.meta.abstracts.AbstractMetaData;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

/**
 * @Description 表类型
 * @Author sunshaoyu
 * @Date 2020年05月27日-14:24
 */
public class TableType extends AbstractMetaData implements Serializable {

    private String tableName;
    private E_TABLETYPE tableType;
    private String category;
    private String extension;

    private Map<String, Element> fieldMap;
    private boolean isVirtual = false;
    private boolean isAbstract = false;

    public TableType(String location, String id, String longName, String tableName,
                     String tableType, String category, String extension, Map<String, Element> fieldMap) {
        super(location, id, longName, SdtConst.TABLE_SUFFIX);
        this.tableName = tableName;
        try{
            this.tableType = E_TABLETYPE.valueOf(tableType.toUpperCase());
        }catch (Exception e){
            this.tableType = E_TABLETYPE.ORDINARY;
        }
        this.category = category;
        this.extension = extension;
        this.fieldMap = fieldMap;
    }

    public String getTableName() {
        return tableName;
    }

    public E_TABLETYPE getTableType() {
        return tableType;
    }

    public String getCategory() {
        return category;
    }

    public String getExtension() {
        return extension;
    }

    public boolean isVirtual() {
        return isVirtual;
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public void setVirtual(boolean virtual) {
        isVirtual = virtual;
    }

    public void setAbstract(boolean anAbstract) {
        isAbstract = anAbstract;
    }

    public Map<String, Element> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, Element> fieldMap) {
        this.fieldMap = fieldMap;
    }

    @Override
    public String toString() {
        return "TableType{" +
                "tableName='" + tableName + '\'' +
                ", tableType=" + tableType +
                ", category='" + category + '\'' +
                ", extension='" + extension + '\'' +
                ", isVirtual=" + isVirtual +
                ", isAbstract=" + isAbstract +
                ", location='" + location + '\'' +
                ", suffix=" + Arrays.toString(suffix) +
                ", id='" + id + '\'' +
                ", longName='" + longName + '\'' +
                '}';
    }
}
