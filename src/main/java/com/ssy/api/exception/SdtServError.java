package com.ssy.api.exception;

import com.ssy.api.entity.constant.ErrCodeDef;
import com.ssy.api.entity.enums.E_PTEMODULE;
import com.ssy.api.utils.system.CommUtil;

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
        if(CommUtil.isNull(params)){
            throw new SdtException(String.format("Fail to retreive data from table [%s], record not found", tableDesc), ErrCodeDef.DB_NO_RECORD);
        }else{
            StringBuffer buffer = new StringBuffer();
            for(String p : params){
                buffer.append(p).append("-");
            }
            String errorParamStr = buffer.substring(0, buffer.lastIndexOf("-"));
            throw new SdtException(String.format("Fail to retreive data from table [%s], record not found, [%s]", tableDesc, errorParamStr), ErrCodeDef.DB_NO_RECORD);
        }
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

    /** 字段值设置非法,必须为指定值 **/
    public static SdtException E0007(String longname, Object originalValue, Object limitValue) {
        throw new SdtException("Invalid value of field "+longname+" ["+originalValue+"], must be: ["+limitValue+"]", ErrCodeDef.FIELD_NO_PASS_CHECK);
    }

    /** 批量执行编号{}的执行状态是{},无法再次执行 **/
    public static SdtException E0008(String batchRunNo, String tranState) {
        throw new SdtException("The execution status of batch execution number ["+batchRunNo+"] is ["+tranState+"] and cannot be executed again", ErrCodeDef.BATCH_TASK_ERROR);
    }

    /** 登录失效 **/
    public static SdtException E0009() {
        throw new SdtException("Login is invalid, please log in again", ErrCodeDef.LOGIN_EXPIRED);
    }

    /** 用户{}不存在 **/
    public static SdtException E0010(String userAcct) {
        throw new SdtException("Account ["+userAcct+"] does not exist", ErrCodeDef.NOTHINGNESS_ACCT);
    }

    /** 用户{}的密码不正确 **/
    public static SdtException E0011(String userAcct) {
        throw new SdtException("Incorrect password for user account [" + userAcct + "]", ErrCodeDef.WRONG_PASSWD);
    }

    /** flowtran{}的输入输出接口字段设置不合法 **/
    public static SdtException E0012(String flowtranId) {
        throw new SdtException("The input and output interface fields of flowtran ["+flowtranId+"] are not legally set", ErrCodeDef.FIELD_NO_PASS_CHECK);
    }

    /** 不支持的PTE模块{} **/
    public static SdtException E0013(E_PTEMODULE pteModule) {
        throw new SdtException("Unsupported PTE module ["+pteModule.getValue()+"]", ErrCodeDef.FIELD_NO_PASS_CHECK);
    }

    /** flowtran id{}对应的文件模型不存在 **/
    public static SdtException E0014(String flowtranId) {
        throw new SdtException("The file model corresponding to the flowtran id ["+flowtranId+"] does not exist", ErrCodeDef.FILE_NOT_EXIST);
    }

    /** 无法找到flowtran编号是{},并且PTE模块是{}的json文件 **/
    public static SdtException E0015(String flowtranId, E_PTEMODULE pteModule) {
        throw new SdtException("Unable to find the json file whose flowtran indicator is ["+flowtranId+"] and the PTE module is ["+pteModule.getValue()+"]", ErrCodeDef.FILE_NOT_EXIST);
    }

    /** 无法检索到列表名称为{}的字段列表 **/
    public static SdtException E0016(String listName) {
        throw new SdtException("Cannot retrieve the field list with list name ["+listName+"]", ErrCodeDef.FIELD_NO_PASS_CHECK);
    }

    /** PTE json{}对应的文件模型不存在 **/
    public static SdtException E0017(String pteJsonName) {
        throw new SdtException("The file model corresponding to the PTE json ["+pteJsonName+"] does not exist", ErrCodeDef.FILE_NOT_EXIST);
    }

    /** SQL运行时错误 **/
    public static SdtException E0018(Exception e) {
        throw new SdtException(e, ErrCodeDef.SQL_ERROR);
    }

    /** 未找到指定的节点{} **/
    public static SdtException E0019(String node) {
        throw new SdtException("The specified node ["+node+"] was not found", ErrCodeDef.DB_NO_RECORD);
    }

    /** 复合类型模型不存在 **/
    public static SdtException E0020(String complexLocaltion) {
        throw new SdtException("The file model corresponding to the complex type location ["+complexLocaltion+"] does not exist", ErrCodeDef.FILE_NOT_EXIST);
    }

    /** 服务类型模型不存在 **/
    public static SdtException E0021(String serviceLocaltion) {
        throw new SdtException("The file model corresponding to the service type location ["+serviceLocaltion+"] does not exist", ErrCodeDef.FILE_NOT_EXIST);
    }
}
