package com.ssy.api.exception;

import com.ssy.api.entity.constant.ErrCodeDef;
import com.ssy.api.utils.system.CommUtil;

/**
 * @Description sunline developer tools全局异常
 * @Author sunshaoyu
 * @Date 2020年06月11日-16:51
 */
public class SdtException extends RuntimeException {

	private final String errorMsg;
	private final String errorCode;

	public SdtException(Throwable cause) {
		super(cause);
		if (cause instanceof SdtException) {
			this.errorCode = ((SdtException) cause).getErrorCode();
			this.errorMsg = ((SdtException) cause).getErrorMsg();
		} else {
			this.errorCode = ErrCodeDef.UNKNOWN_ERROR;
			this.errorMsg = cause.getMessage();
		}
	}

	public SdtException(Throwable cause, String errorCode) {
		super(cause);
		if (cause instanceof SdtException) {
			this.errorCode = ((SdtException) cause).getErrorCode();
			this.errorMsg = ((SdtException) cause).getErrorMsg();
		} else {
			this.errorCode = errorCode;
			this.errorMsg = cause.getMessage();
		}
	}

	public SdtException(String message, Throwable cause) {
		super(message, cause);
		if (cause instanceof SdtException) {
			this.errorMsg = ((SdtException) cause).getErrorMsg();
			this.errorCode = ((SdtException) cause).getErrorCode();
		} else {
			this.errorCode = ErrCodeDef.SYSTEM_ERROR;
			this.errorMsg = cause.getMessage();
		}
	}

	public SdtException(String errorMsg, String errorCode) {
		super("[" + CommUtil.nvl(errorCode, ErrCodeDef.UNKNOWN_ERROR) + "]" + errorMsg);
		this.errorMsg = errorMsg;
		this.errorCode = errorCode;
	}

	public SdtException(String errorMsg, String errorCode, Throwable e) {
		super("[" + CommUtil.nvl(errorCode, ErrCodeDef.UNKNOWN_ERROR) + "]" + errorMsg, e);
		this.errorMsg = errorMsg;
		this.errorCode = errorCode;
	}

	public SdtException(String errorMsg) {
		super("[" + ErrCodeDef.SYSTEM_ERROR + "]" + errorMsg);
		this.errorMsg = errorMsg;
		this.errorCode = ErrCodeDef.SYSTEM_ERROR;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}
}
