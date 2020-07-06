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
    public static SdtException E0003(String tableDesc, String... params) {
        StringBuffer buffer = new StringBuffer();
        for(String p : params){
            buffer.append(p).append("-");
        }
        String errorParamStr = buffer.substring(0, buffer.lastIndexOf("-"));

        throw new SdtException(String.format("Fail to retreive data from table [%s], record not found, [%s]", tableDesc, errorParamStr), ErrCodeDef.DB_NO_RECORD);
    }

    /** A必须大于B **/
    public static SdtException E0004(String realName, Object realValue, String limitName, Object limitValue) {
        throw new SdtException(realName + "["+realValue+"] must be greater than "+limitName+"["+limitValue+"]", ErrCodeDef.FIELD_NO_PASS_CHECK);
    }

    /** A必须大于或等于B **/
    public static SdtException E0005(String realName, Object realValue, String limitName, Object limitValue) {
        throw new SdtException(realName + "["+realValue+"] must be greater than or euqal to "+limitName+"["+limitValue+"]", ErrCodeDef.FIELD_NO_PASS_CHECK);
    }

    /** 批量任务处理失败 **/
    public static SdtException E0006(String systemCode) {
        throw new SdtException("Batch task of system ["+systemCode+"] processing failed", ErrCodeDef.BATCH_TASK_ERROR);
    }
}
