package com.ssy.api.entity.constant;

import java.io.File;

/**
 * @Description 系统常量
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
    /** session中的运行时环境变量名 **/
    public static final String RUN_ENVS = "runEnvs";
    /** 交易流水的长度 **/
    public static final int TRXN_SEQ_LENGTH = 25;
    /** 当前用户的session的key **/
    public static final String CURRENT_USER = "currentUser";
    /** 用户登录最大有效时间 **/
    public static final int userMaxInactiveInterval = 30 * 60;
    /** 项目基础路径 **/
    public static final String BASE_PATH = "basePath";
    /** POST请求方式 **/
    public static final String POST_REQUEST = "POST";
    /** GET请求方式 **/
    public static final String GET_REQUEST = "GET";

    /** 加密秘钥入参名 **/
    public static final String ENCKEY = "encKey";
    /** 数据实体入参名 **/
    public static final String PARAMS = "params";
    /** 加密秘钥随机数的长度 **/
    public static final int ENCKEY_RAND_LENTH = 512;
    /** aes随机数秘钥 **/
    public static final String AES_RAND_KEY = "AES_RAND_KEY";
    /** aes加密秘钥 **/
    public static final String AES_ENC_KEY = "AES_ENC_KEY";
    /** 敏感字段关键字 **/
    public static final String[] SENSITIVE_FIELD_ARRAY = {"pwd"};


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

    /** 动态数据源 **/
    public static final String MASTER_DATASOURCE = "masterDS";
    public static final String DYNAMIC_DATASOURCE = "dynamicDS";
    public static final String DYNAMIC_SQL_SESSION_FACTORY = "sqlSessionFactoryBeanDynamic";

    /** 日终批量 **/
    public static final String EXE_GROUP_SPLIT_TOKEN = ";";
    public static final String DAY_SWITCH_FLOW_ID = "Switch";
    public static final String BATCH_EXECUTE_MODE = "1";//按批量交易流程执行

    /** 参数相关 **/
    public static final String DEFAULT_TELLER = "DEFAULT_TELLER";
}
