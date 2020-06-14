package com.ssy.api.exception;

import com.ssy.api.entity.constant.ErrCodeDef;
import com.ssy.api.utils.CommUtil;

/**
 * @Description sunline developer tools全局异常
 * @Author sunshaoyu
 * @Date 2020年06月11日-16:51
 */
public class SdtException extends RuntimeException {

    private String errorMsg;
    private String errorCode;

    public SdtException(String errorMsg, String errorCode) {
        super("[" + CommUtil.nvl(errorCode, ErrCodeDef.UNKNOWN_ERROR) + "]:" + errorMsg);
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
