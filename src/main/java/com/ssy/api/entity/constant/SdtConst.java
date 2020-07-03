package com.ssy.api.entity.constant;

import java.io.File;

/**
 * @Description
 * @Author sunshaoyu
 * @Date 2020年06月11日-13:58
 */
public class SdtConst {

    /** 默认编码 **/
    public static final String DEFAULT_ENCODING = "UTF-8";
    /** 通配符 **/
    public static final String WILDCARD = "*";
    /** java资源文件目录 **/
    public static final String JAVA_RESOURCES_PATH =
            new StringBuffer("src").append(File.separator).append("main").append(File.separator).append("resources").toString();
    /** 微服务模型的正则表达式 **/
    public static final String MS_MODEL_REG = "^Ms.*?";

    /** 元数据模型文件后缀及节点名称 **/
    public static final String COMPLEX_TYPE_NODE_NAME = "complexType";
    public static final String ELEMENT_NODE_NAME = "element";
    public static final String RESTRICTIONTYPE_NODE_NAME = "restrictionType";
    public static final String ENUMERATION_NODE_NAME = "enumeration";
    public static final String TABLE_NODE_NAME = "table";
    public static final String FIELD_NODE_NAME = "field";
    public static final int DEFAULT_RESTRICTION_LENGTH = 20;
    public static final int DEFAULT_RESTRICTION_DIGITS = 0;
    public static final String DICT_SUFFIX = ".d_schema.xml";
    public static final String COMPLEX_SUFFIX = ".c_schema.xml";
    public static final String ENUM_SUFFIX = ".e_schema.xml";
    public static final String REUSABLE_SUFFIX = ".u_schema.xml";
    public static final String TABLE_SUFFIX = ".tables.xml";
    public static final String INTF_EXCEL_SUFFIX = ".xlsx";
    public static final String[] PROJECT_FILE_SUFFIX = {".xml"};

    /** 动态数据源配置 **/
    public static final String MASTER_DATASOURCE = "masterDS";
    public static final String DYNAMIC_DATASOURCE = "dynamicDS";
    public static final String DYNAMIC_SQL_SESSION_FACTORY = "sqlSessionFactoryBeanDynamic";
}
