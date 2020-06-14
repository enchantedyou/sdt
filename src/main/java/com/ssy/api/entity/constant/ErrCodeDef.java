package com.ssy.api.entity.constant;

/**
 * @Description 错误码定义
 * @Author sunshaoyu
 * @Date 2020年06月11日-17:18
 */
public class ErrCodeDef {

    /** 交易成功 **/
    public static final String SUCCESS = "0000";
    /** 重复的请求 **/
    public static final String REPEATE_REQUEST = "1006";
    /** 数字或金额不合法 **/
    public static final String INVALID_NUMBER = "1010";
    /** 数据未改变，不能维护 **/
    public static final String DATA_NO_CHANGE = "1011";
    /** 请求超时 **/
    public static final String REQUEST_TIME_OUT = "1999";
    /** 账户不存在 **/
    public static final String NOTHINGNESS_ACCT = "2001";
    /** 密码错误 **/
    public static final String WRONG_PASSWD = "2004";
    /** 主键或唯一索引冲突 **/
    public static final String DB_PK_CONFLICT = "8001";
    /** 数据无对应记录 **/
    public static final String DB_NO_RECORD = "8003";
    /** 数据维护时出现丢失更新 **/
    public static final String DB_UPDATE_LOST = "8005";
    /** 信息域或逻辑未通过检查 **/
    public static final String FIELD_NO_PASS_CHECK = "9000";
    /** 信息域不允许为空 **/
    public static final String FIELD_NOT_INPUT = "9001";
    /** 信息域格式不合法 **/
    public static final String FIELD_INVALID_FORMAT = "9002";
    /** 信息域长度超过限制 **/
    public static final String FIELD_TOO_LONG = "9003";
    /** 信息域不允许有值 **/
    public static final String FIELD_NOT_ALLOW_INPUT = "9014";
    /** 数据加密失败 **/
    public static final String ENCRYPT_FAILURE = "9017";
    /** 数据解密失败 **/
    public static final String DECRYPT_FAILURE = "9018";
    /** xml文件处理失败 **/
    public static final String XML_DEAL_FAILURE = "9020";
    /** 未知错误 **/
    public static final String UNKNOWN_ERROR = "9999";
}
