package com.ssy.api.exception;

import com.ssy.api.entity.constant.ErrCodeDef;

/**
 * @Description
 * @Author sunshaoyu
 * @Date 2020年06月11日-17:36
 */
public class ApPubErr {

    /** 信息域不能为空 **/
    public static SdtException E0001(String fieldName, String fieldDesc) {
        throw new SdtException("Field ["+fieldName+"-"+fieldDesc+"] is mandatory", ErrCodeDef.FIELD_NOT_INPUT);
    }

    /** 信息域不允许输入 **/
    public static SdtException E0002(String fieldDesc) {
        throw new SdtException("["+fieldDesc+"] not allowed to input", ErrCodeDef.FIELD_NOT_ALLOW_INPUT);
    }

    /** 待审计的对象不一致 **/
    public static SdtException E0003(String oldOdbName, String newOdbName) {
        throw new SdtException("Objects to be audited are inconsistent ["+oldOdbName+"-"+newOdbName+"]", ErrCodeDef.FIELD_NO_PASS_CHECK);
    }

    /** 数据未改变，不能维护 **/
    public static SdtException E0004() {
        throw new SdtException("There is no change to this maintenance", ErrCodeDef.DATA_NO_CHANGE);
    }

    /** 表数据已被他人更新 **/
    public static SdtException E0005(String tableDesc) {
        throw new SdtException("Data table ["+tableDesc+"] has been updated by others, please get latest and try again", ErrCodeDef.DB_UPDATE_LOST);
    }

    /** 表数据已存在 **/
    public static SdtException E0006(String tableDesc, String keyValue) {
        throw new SdtException("Record exists in the data table ["+tableDesc+"],key value ["+keyValue+"]", ErrCodeDef.DB_PK_CONFLICT);
    }

    /** 元数据模型加载失败 **/
    public static SdtException E0007(Throwable e) {
        throw new SdtException("Failed to load metadata model", ErrCodeDef.XML_DEAL_FAILURE);
    }

    /** 元数据模型的优先级未配置 **/
    public static SdtException E0008(String modelName) {
        throw new SdtException("The priority of metadata model ["+modelName+"] is not configured or not effective", ErrCodeDef.DB_NO_RECORD);
    }

    /** 动态数据源不存在 **/
    public static SdtException E0009(String dataSource) {
        throw new SdtException("Dynamic data source ["+dataSource+"] does not exist", ErrCodeDef.DB_NO_RECORD);
    }
}
