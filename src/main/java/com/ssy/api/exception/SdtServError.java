package com.ssy.api.exception;

import com.ssy.api.entity.constant.ErrCodeDef;

/**
 * @Description 开发工具助手业务类错误
 * @Author sunshaoyu
 * @Date 2020年06月12日-15:42
 */
public class SdtServError {

    /** 值必须大于0 **/
    public static SdtException E0001(String fieldValue, String fieldDesc) {
        throw new SdtException(fieldDesc + " ["+fieldValue+"] must be greater than 0", ErrCodeDef.INVALID_NUMBER);
    }

    /** 值必须大于等于0 **/
    public static SdtException E0002(String fieldValue, String fieldDesc) {
        throw new SdtException(fieldDesc + " ["+fieldValue+"] must be greater than or equal to 0", ErrCodeDef.INVALID_NUMBER);
    }

    /** 查询表的记录不存在 **/
    public static SdtException E0003(String tableDesc) {
        throw new SdtException("The record of the query table ["+tableDesc+"] does not exist", ErrCodeDef.DB_NO_RECORD);
    }
}
