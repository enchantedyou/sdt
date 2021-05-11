package com.ssy.api.entity.constant;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @Description 系统常量
 * @Author sunshaoyu
 * @Date 2020年06月11日-13:58
 */
public class SdtConst {

	/** 默认编码 **/
	public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
	/** 通配符 **/
	public static final String WILDCARD = "*";
	/** java资源文件目录 **/
	public static final String JAVA_RESOURCES_PATH = new StringBuffer("src").append(File.separator).append("main")
			.toString();
	/** session中的运行时环境变量名 **/
	public static final String RUN_ENVS = "runEnvs";
	/** 交易流水的长度 **/
	public static final int TRXN_SEQ_LENGTH = 25;
	/** 当前用户的session的key **/
	public static final String USER_INFO = "userInfo";
	/** 用户登录最大有效时间 **/
	public static final int userMaxInactiveInterval = 60 * 15;
	/** 项目基础路径 **/
	public static final String BASE_PATH = "basePath";
	/** POST请求方式 **/
	public static final String POST_REQUEST = "POST";
	/** GET请求方式 **/
	public static final String GET_REQUEST = "GET";
	/** 当期页 **/
	public static final String CURRENT_PAGE = "currentPage";
	/** 每页数据量 **/
	public static final String PAGE_SIZE = "pageSize";
	/** 响应数据 **/
	public static final String RESPONSE_DATA = "responseData";
	/** 列表分隔符 **/
	public static final String LIST_SPLIT_TOKEN = ";";
	/** 默认数据源类型 **/
	public static final String DATASOURCE_TYPE = "MYSQL";

	/** 加密秘钥入参名 **/
	public static final String ENCKEY = "encKey";
	/** 数据实体入参名 **/
	public static final String PARAMS = "params";
	/** 配置参数加密秘钥 **/
	public static final String CONFIG_ENCKEY = "Enchantedyou";
	/** 加密秘钥随机数的长度 **/
	public static final int ENCKEY_RAND_LENTH = 512;
	/** 敏感字段关键字 **/
	public static final String[] SENSITIVE_FIELD_ARRAY = { "pwd" };

	/** 元数据模型文件后缀及节点名称 **/
	public static final String COMPLEX_TYPE_NODE_NAME = "complexType";
	public static final String ELEMENT_NODE_NAME = "element";
	public static final String RESTRICTIONTYPE_NODE_NAME = "restrictionType";
	public static final String ENUMERATION_NODE_NAME = "enumeration";
	public static final String TABLE_NODE_NAME = "table";
	public static final String FIELD_NODE_NAME = "field";
	public static final int DEFAULT_RESTRICTION_LENGTH = -1;
	public static final int DEFAULT_RESTRICTION_DIGITS = 0;
	public static final String DICT_SUFFIX = ".d_schema.xml";
	public static final String COMPLEX_SUFFIX = ".c_schema.xml";
	public static final String ENUM_SUFFIX = ".e_schema.xml";
	public static final String REUSABLE_SUFFIX = ".u_schema.xml";
	public static final String TABLE_SUFFIX = ".tables.xml";
	public static final String INTF_EXCEL_SUFFIX = ".xlsx";
	public static final String FLOWTRAN_SUFFIX = ".flowtrans.xml";
	public static final String SERVICETYPE_SUFFIX = ".serviceType.xml";
	public static final String REPORT_SUFFIX = ".report.xml";
	public static final String NAMEDSQL_SUFFIX = ".nsql.xml";
	public static final String JAVA_SUFFIX = ".java";
	public static final String[] PROJECT_FILE_SUFFIX = { "xml", "java" };

	/** 动态数据源 **/
	public static final String MASTER_DATASOURCE = "masterDS";
	public static final String DYNAMIC_DATASOURCE = "dynamicDS";
	public static final String DYNAMIC_SQL_SESSION_FACTORY = "sqlSessionFactoryBeanDynamic";

	/** 日终批量 **/
	public static final String EXE_GROUP_SPLIT_TOKEN = ";";
	public static final String DAY_SWITCH_FLOW_ID = "Switch";
	public static final String BATCH_EXECUTE_MODE = "1";// 按批量交易流程执行
	public static final String FLOW_GROUP = "default";

	/** 参数相关 **/
	public static final String DEFAULT_TELLER = "DEFAULT_TELLER";
	public static final String AES_RAND_KEY = "AES_RAND_KEY";
	public static final String AES_ENC_KEY = "AES_ENC_KEY";

	/** pte相关 **/
	public static final int FOLD_LINE_NUMBER = 3;
	public static final String DEFAULT_REQUEST_URL = "/SUMP/call/RPCCall";
	public static final String DEFAULT_DICT_FORMAT = "value-label";
	public static final String DEFAULT_GRID_SCOPE = "searchTable";
	public static final String DEFAULT_TABS_SCOPE = "templateScope";
	public static final String CURRENCY_BASE_TYPE = "U_CURRENCY";
	public static final String DATE_BASE_TYPE = "U_DATE";
	public static final String DATE_TIME_BASE_TYPE = "U_DATETIME";
	public static final String TIME_BASE_TYPE = "U_TIME";
	public static final int DECIMAL_DIGIT = 2;
	public static final String PTE_SAVE_PATH = "temp/PTE/";

	/** 正则表达式 **/
	public static final String MS_MODEL_REG = "^Ms.*?";
	public static final String CURRENCY_REG = "^[0-9]+([.][0-9]{1,2})?$";

	/** redis缓存相关 **/
	public static final long REDIS_FLOWTRAN_TIMEOUT = 60 * 10;// 10分钟
	public static final long REDIS_GITDIFFS_TIMEOUT = 60 * 60;// 1小时
	public static final long REDIS_SEQVALUE_TIMEOUT = 60 * 60 * 24 * 365;// 一年
}
